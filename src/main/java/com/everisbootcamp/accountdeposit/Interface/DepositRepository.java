package com.everisbootcamp.accountdeposit.Interface;

import java.util.Date;

import com.everisbootcamp.accountdeposit.Data.Deposit;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import reactor.core.publisher.Flux;

public interface DepositRepository extends ReactiveMongoRepository< Deposit, String> {
    Flux<Deposit> findByDate(Date date);
    Flux<Deposit> findByNumberaccount(String numberaccount);
}
