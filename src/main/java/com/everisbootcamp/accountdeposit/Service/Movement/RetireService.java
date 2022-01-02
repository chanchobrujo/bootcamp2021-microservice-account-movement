package com.everisbootcamp.accountdeposit.Service.Movement;

import com.everisbootcamp.accountdeposit.Constants.Enums.Messages.MessagesError;
import com.everisbootcamp.accountdeposit.Model.Request.RequestMovement;
import com.everisbootcamp.accountdeposit.Model.Response.Response;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class RetireService {

    public Mono<Response> save(String numberaccount, RequestMovement model) {
        Response response = new Response(MessagesError.NOTFOUND_DATA);
        return Mono.just(response);
    }
}
