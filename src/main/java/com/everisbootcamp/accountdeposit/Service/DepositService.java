package com.everisbootcamp.accountdeposit.Service;

import java.util.Map;

import com.everisbootcamp.accountdeposit.Constants.Constants;
import com.everisbootcamp.accountdeposit.Data.Deposit;
import com.everisbootcamp.accountdeposit.Interface.DepositRepository;
import com.everisbootcamp.accountdeposit.Model.AccountModel;
import com.everisbootcamp.accountdeposit.Model.DepositModel;
import com.everisbootcamp.accountdeposit.Model.ResponseModel;
import com.everisbootcamp.accountdeposit.Web.Consumer;

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

    public Mono<ResponseEntity<Map<String, Object>>> BindingResultErrors(BindingResult bindinResult) {
        ResponseModel response = new ResponseModel(
            bindinResult.getAllErrors().stream().findFirst().get().getDefaultMessage().toString(),
            HttpStatus.NOT_ACCEPTABLE
        );

        return Mono.just(ResponseEntity.internalServerError().body(response.getResponse()));
    }

    public Mono<ResponseModel> save(String numberaccount, DepositModel model) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        String message = Constants.Messages.INVALID_DATA; 

        if (findAccountByNumberAccount(numberaccount).getBody() != null) {
            status = HttpStatus.CREATED;
            message = Constants.Messages.CORRECT_DATA;

            //add amount at account.
 
            repository.save( new Deposit(numberaccount, model.getAmount()) ).subscribe();
        } 

        return Mono.just(new ResponseModel(message, status));
    }
    
    public Flux<Deposit> findAll() {
        return repository.findAll();
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
