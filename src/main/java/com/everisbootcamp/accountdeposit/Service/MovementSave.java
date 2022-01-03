package com.everisbootcamp.accountdeposit.Service;

import com.everisbootcamp.accountdeposit.Common.Utils;
import com.everisbootcamp.accountdeposit.Constants.Enums.Messages.MessagesError;
import com.everisbootcamp.accountdeposit.Constants.Enums.Messages.MessagesSuccess;
import com.everisbootcamp.accountdeposit.Data.Movement;
import com.everisbootcamp.accountdeposit.Interface.MovementRepository;
import com.everisbootcamp.accountdeposit.Model.Response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovementSave {

    @Autowired
    private MovementRepository movementRepository;

    public Response save(String TYPE, String NUM, Double AMOUNT) {
        Response response = new Response();
        try {
            Movement movement = Movement
                .builder()
                .typemovement(TYPE)
                .numberaccount(NUM)
                .amount(AMOUNT)
                .datecreated(Utils.date())
                .build();

            movementRepository.save(movement).subscribe();
            response = new Response(MessagesSuccess.SUCCESS_REGISTER);
        } catch (Exception e) {
            response = new Response(MessagesError.MOVEMENT_DENIED);
        }
        return response;
    }
}
