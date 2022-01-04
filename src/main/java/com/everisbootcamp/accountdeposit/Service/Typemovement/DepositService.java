package com.everisbootcamp.accountdeposit.Service.Typemovement;

import com.everisbootcamp.accountdeposit.Common.Utils;
import com.everisbootcamp.accountdeposit.Constants.Constan;
import com.everisbootcamp.accountdeposit.Constants.Enums.Messages.MessagesError;
import com.everisbootcamp.accountdeposit.Constants.Enums.Messages.MessagesSuccess;
import com.everisbootcamp.accountdeposit.Data.Movement;
import com.everisbootcamp.accountdeposit.Interface.MovementRepository;
import com.everisbootcamp.accountdeposit.Model.Request.RequestMovement;
import com.everisbootcamp.accountdeposit.Model.Request.RequestUpdateBalance;
import com.everisbootcamp.accountdeposit.Model.Response.Response;
import com.everisbootcamp.accountdeposit.Service.Accounts.AccountService;
import com.everisbootcamp.accountdeposit.Service.DetailService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class DepositService {

    @Autowired
    private AccountService accountService;

    @Autowired
    private MovementRepository movementRepository;

    @Autowired
    private DetailService detailService;

    public Mono<Response> save(String numberaccount, RequestMovement model) {
        Response response = new Response(MessagesError.NOTFOUND_DATA);

        Double balance = this.accountService.AmountAccount(numberaccount) + model.getAmount();
        RequestUpdateBalance modelBal = new RequestUpdateBalance(numberaccount, balance);
        System.err.println(this.accountService.updateBalanceAccount(modelBal));

        String client = model
            .getDni()
            .concat(Constan.PLUS)
            .concat(model.getNombre())
            .concat(Constan.PLUS)
            .concat(model.getApellido());

        Map<String, String> detail = this.detailService.defineDetails(client, model.getRazon());

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

        return Mono.just(response);
    }
}
