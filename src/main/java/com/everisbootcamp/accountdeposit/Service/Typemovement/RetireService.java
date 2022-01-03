package com.everisbootcamp.accountdeposit.Service.Typemovement;

import com.everisbootcamp.accountdeposit.Constants.Enums.Messages.MessagesError;
import com.everisbootcamp.accountdeposit.Model.Request.RequestMovement;
import com.everisbootcamp.accountdeposit.Model.Request.RequestUpdateBalance;
import com.everisbootcamp.accountdeposit.Model.Response.Response;
import com.everisbootcamp.accountdeposit.Service.Accounts.AccountService;
import com.everisbootcamp.accountdeposit.Service.MovementSave;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class RetireService {

    @Autowired
    private MovementSave movementSave;

    @Autowired
    private AccountService accountService;

    public Mono<Response> save(String numberaccount, RequestMovement model) {
        Response response = new Response(MessagesError.AMOUNT_INSUFFICIENT);

        Double balance = this.accountService.AmountAccount(numberaccount);
        Boolean verifyAmount = balance >= model.getAmount();
        if (verifyAmount) {
            balance = balance - model.getAmount();
            RequestUpdateBalance modelBal = new RequestUpdateBalance(numberaccount, balance);
            System.err.println(this.accountService.updateBalanceAccount(modelBal));

            response = movementSave.save(model.getTypemovement(), numberaccount, model.getAmount());
        }

        return Mono.just(response);
    }
}
