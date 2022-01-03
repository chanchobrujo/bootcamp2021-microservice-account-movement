package com.everisbootcamp.accountdeposit.Service.Typemovement;

import com.everisbootcamp.accountdeposit.Common.Utils;
import com.everisbootcamp.accountdeposit.Constants.Enums.Messages.MessagesError;
import com.everisbootcamp.accountdeposit.Constants.Enums.Messages.MessagesSuccess;
import com.everisbootcamp.accountdeposit.Data.Movement;
import com.everisbootcamp.accountdeposit.Interface.MovementRepository;
import com.everisbootcamp.accountdeposit.Model.Request.RequestMovement;
import com.everisbootcamp.accountdeposit.Model.Request.RequestUpdateBalance;
import com.everisbootcamp.accountdeposit.Model.Response.Response;
import com.everisbootcamp.accountdeposit.Service.Accounts.AccountService;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class RetireService {

    @Autowired
    private MovementRepository movementRepository;

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

            Map<String, String> detail = new HashMap<>();

            if (!Utils.StringEmpty(model.getRazon())) {
                detail.put("Razón de la operación", model.getRazon());
            }

            Movement movement = Movement
                .builder()
                .typemovement(model.getTypemovement())
                .numberaccount(numberaccount)
                .amount(model.getAmount())
                .datecreated(Utils.date())
                .details(detail)
                .build();

            movementRepository.save(movement).subscribe();
            response = new Response(MessagesSuccess.SUCCESS_REGISTER);
        }

        return Mono.just(response);
    }
}
