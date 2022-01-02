package com.everisbootcamp.accountdeposit.Service.Movement;

import com.everisbootcamp.accountdeposit.Constants.Enums.Messages.MessagesError;
import com.everisbootcamp.accountdeposit.Constants.Enums.Messages.MessagesSuccess;
import com.everisbootcamp.accountdeposit.Data.Deposit;
import com.everisbootcamp.accountdeposit.Interface.DepositRepository;
import com.everisbootcamp.accountdeposit.Model.Request.RequestMovement;
import com.everisbootcamp.accountdeposit.Model.Request.RequestUpdateBalance;
import com.everisbootcamp.accountdeposit.Model.Response.Response;
import com.everisbootcamp.accountdeposit.Service.AccountService;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class DepositService {

    @Autowired
    private DepositRepository repository;

    // @Autowired
    // private RuleService ruleService;

    @Autowired
    private AccountService accountService;

    public Mono<Response> save(String numberaccount, RequestMovement model) {
        Response response = new Response(MessagesError.NOTFOUND_DATA);

        Boolean verifyExistAccount = Objects.nonNull(
            this.accountService.findAccountByNumberAccount(numberaccount)
        );

        if (verifyExistAccount) {
            // if (!this.ruleService.verifyRules(numberaccount)) {
            // response = new Response(MessagesError.MOVEMENT_DENIED);
            // } else {
            Double balance = this.accountService.AmountAccount(numberaccount) + model.getAmount();
            RequestUpdateBalance modelBal = new RequestUpdateBalance(numberaccount, balance);
            System.err.println(this.accountService.updateBalanceAccount(modelBal));

            repository.save(new Deposit(numberaccount, model.getAmount())).subscribe();
            response = new Response(MessagesSuccess.SUCCESS_REGISTER);
            // }
        }

        return Mono.just(response);
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
