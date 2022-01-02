package com.everisbootcamp.accountdeposit.Service;

import com.everisbootcamp.accountdeposit.Common.Utils;
import com.everisbootcamp.accountdeposit.Constants.Enums.Messages.MessagesError;
import com.everisbootcamp.accountdeposit.Constants.Enums.Types.TypeMovement;
import com.everisbootcamp.accountdeposit.Model.Request.RequestMovement;
import com.everisbootcamp.accountdeposit.Model.Response.Response;
import com.everisbootcamp.accountdeposit.Service.Accounts.RuleService;
import com.everisbootcamp.accountdeposit.Service.Movement.DepositService;
import com.everisbootcamp.accountdeposit.Service.Movement.RetireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class TypeMovementService {

    @Autowired
    private DepositService depositService;

    @Autowired
    private RetireService retireService;

    @Autowired
    private RuleService ruleService;

    private Boolean verifyExistMovement(String MOV) {
        return TypeMovement.FindByName(MOV).isPresent();
    }

    private Boolean verifyRules(String numberaccount) {
        return this.ruleService.verifyRules(numberaccount);
    }

    public Mono<Response> initMovement(String numberaccount, RequestMovement model) {
        Response response = new Response(MessagesError.NOTFOUND_DATA);

        String TYPEM = model.getTypemovement();

        if (this.verifyExistMovement(TYPEM)) {
            if (this.verifyRules(numberaccount)) {
                response = new Response(MessagesError.MOVEMENT_DENIED);
            } else {
                String DENAME = TypeMovement.DEPOSIT.getName();
                String RENAME = TypeMovement.RETIRE.getName();

                Boolean verifyDeposit = Utils.equalsOrContains(TYPEM, DENAME);
                Boolean verifyRetire = Utils.equalsOrContains(TYPEM, RENAME);

                if (verifyDeposit) {
                    return this.depositService.save(numberaccount, model);
                } else if (verifyRetire) {
                    return this.retireService.save(numberaccount, model);
                }
            }
        }

        return Mono.just(response);
    }
}
