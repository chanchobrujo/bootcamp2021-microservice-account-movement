package com.everisbootcamp.accountdeposit.Interface;

import com.everisbootcamp.accountdeposit.Data.Movement;
import java.util.Date;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface MovementRepository extends ReactiveMongoRepository<Movement, String> {
    Flux<Movement> findByDatecreated(Date date);

    Flux<Movement> findByNumberaccount(String numberaccount);
}
