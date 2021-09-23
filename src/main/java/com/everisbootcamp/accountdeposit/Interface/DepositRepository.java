package com.everisbootcamp.accountdeposit.Interface;

import com.everisbootcamp.accountdeposit.Data.Deposit;
import java.util.Date;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface DepositRepository extends ReactiveMongoRepository<Deposit, String> {
    Flux<Deposit> findByDatecreated(Date date);
    Flux<Deposit> findByNumberaccount(String numberaccount);
}
