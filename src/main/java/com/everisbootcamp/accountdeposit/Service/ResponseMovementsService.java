package com.everisbootcamp.accountdeposit.Service;

import com.everisbootcamp.accountdeposit.Data.Movement;
import com.everisbootcamp.accountdeposit.Interface.MovementRepository;
import com.everisbootcamp.accountdeposit.Model.Response.ResponseMovement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class ResponseMovementsService {

    @Autowired
    private MovementRepository movementRepository;

    public Flux<ResponseMovement> findAll() {
        List<Movement> findAll =
            this.movementRepository.findAll().toStream().collect(Collectors.toList());
        List<ResponseMovement> CollectionResponse = new ArrayList<ResponseMovement>();

        for (Movement movement : findAll) {
            ResponseMovement responseMovement = ResponseMovement
                .builder()
                .type(movement.getTypemovement())
                .amount(movement.getAmount())
                .numberaccount(movement.getNumberaccount())
                .datecreated(movement.getDatecreated())
                .details(movement.getDetails())
                .build();

            CollectionResponse.add(responseMovement);
        }

        return Flux.fromIterable(CollectionResponse);
    }
}
