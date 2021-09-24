package com.everisbootcamp.accountdeposit.Service;

import com.everisbootcamp.accountdeposit.Constants.Constants;
import com.everisbootcamp.accountdeposit.Data.Deposit;
import com.everisbootcamp.accountdeposit.Interface.DepositRepository;
import com.everisbootcamp.accountdeposit.Model.AccountModel;
import com.everisbootcamp.accountdeposit.Model.DepositModel;
import com.everisbootcamp.accountdeposit.Model.ResponseModel;
import com.everisbootcamp.accountdeposit.Model.RulesModel;
import com.everisbootcamp.accountdeposit.Model.updateBalanceModel;
import com.everisbootcamp.accountdeposit.Web.Consumer;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class DepositService {

    @Autowired
    private DepositRepository repository;

    private ResponseEntity<AccountModel> findAccountByNumberAccount(String number) {
        return Consumer.webclientAccount
            .get()
            .uri("/".concat(number))
            .retrieve()
            .onStatus(status -> status.value() == 404, clientResponse -> Mono.empty())
            .toEntity(AccountModel.class)
            .block();
    }

    private void updateBalance(String numberaccount, Double balance) {
        Consumer.webclientAccount
            .post()
            .uri("/updateBalance")
            .body(
                Mono.just(new updateBalanceModel(numberaccount, balance)),
                updateBalanceModel.class
            )
            .retrieve()
            .bodyToMono(Object.class)
            .subscribe();
    }

    private int getMonthlyMovementsQuantity(String numberaccount) {
        return (int) repository
            .findAll()
            .toStream()
            .filter(d -> d.getNumberaccount().equals(numberaccount))
            .count();
    }

    public Mono<ResponseEntity<Map<String, Object>>> BindingResultErrors(
        BindingResult bindinResult
    ) {
        ResponseModel response = new ResponseModel(
            bindinResult
                .getAllErrors()
                .stream()
                .findFirst()
                .get()
                .getDefaultMessage()
                .toString(),
            HttpStatus.NOT_ACCEPTABLE
        );

        return Mono.just(
            ResponseEntity.internalServerError().body(response.getResponse())
        );
    }

    public Mono<ResponseModel> save(String numberaccount, DepositModel model) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        String message = Constants.Messages.INVALID_DATA;

        if (findAccountByNumberAccount(numberaccount).getBody() != null) {
            RulesModel rules = findAccountByNumberAccount(numberaccount)
                .getBody()
                .getRules();

            if (
                rules.isMaximumLimitMonthlyMovements() &&
                rules.getMaximumLimitMonthlyMovementsQuantity() <=
                getMonthlyMovementsQuantity(numberaccount)
            ) return Mono.just(
                new ResponseModel(
                    Constants.Messages.MOVEMENT_DENIED,
                    HttpStatus.NOT_ACCEPTABLE
                )
            );

            status = HttpStatus.CREATED;
            message = Constants.Messages.CORRECT_DATA;

            Double newamount =
                findAccountByNumberAccount(numberaccount).getBody().getAmount() +
                model.getAmount();
            updateBalance(numberaccount, newamount);

            repository.save(new Deposit(numberaccount, model.getAmount())).subscribe();
        }

        return Mono.just(new ResponseModel(message, status));
    }

    public Flux<Deposit> findAll() {
        return repository.findAll();
    }

    public Flux<Deposit> findByNumberAccount(String numberaccount) {
        return Flux.fromIterable(
            repository
                .findAll()
                .toStream()
                .filter(r -> r.getNumberaccount().equals(numberaccount))
                .collect(Collectors.toList())
        );
    }

    public Mono<Deposit> findById(String id) {
        return repository.findById(id);
    }

    public Flux<Deposit> findByDatecreated(String date) {
        return repository.findByDatecreated(null);
    }

    public Flux<Deposit> findByNumberaccount(String numberaccount) {
        return repository.findByNumberaccount(numberaccount);
    }
}
