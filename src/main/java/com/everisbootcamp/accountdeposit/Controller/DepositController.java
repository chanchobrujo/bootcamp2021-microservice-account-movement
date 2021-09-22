package com.everisbootcamp.accountdeposit.Controller;

import com.everisbootcamp.accountdeposit.Data.Deposit;
import com.everisbootcamp.accountdeposit.Service.DepositService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping
public class DepositController {
    @Autowired
    private DepositService service;

    @GetMapping("/")
    public Mono<ResponseEntity<Flux<Deposit>>> findByAll() {
        return Mono.just(ResponseEntity.ok().body(service.findAll()));
    }
}
