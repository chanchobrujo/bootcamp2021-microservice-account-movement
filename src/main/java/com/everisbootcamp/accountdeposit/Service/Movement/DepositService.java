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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class DepositService {

    @Autowired
    private DepositRepository repository;

    @Autowired
    private AccountService accountService;

    public Mono<Response> save(String numberaccount, RequestMovement model) {
        Response response = new Response(MessagesError.NOTFOUND_DATA);

        Boolean verifyExistAccount = Objects.nonNull(
            this.accountService.findAccountByNumberAccount(numberaccount)
        );

        if (verifyExistAccount) {
            Double balance = this.accountService.AmountAccount(numberaccount) + model.getAmount();
            RequestUpdateBalance modelBal = new RequestUpdateBalance(numberaccount, balance);
            System.err.println(this.accountService.updateBalanceAccount(modelBal));

            repository.save(new Deposit(numberaccount, model.getAmount())).subscribe();
            response = new Response(MessagesSuccess.SUCCESS_REGISTER);
        }

        return Mono.just(response);
    }
}
