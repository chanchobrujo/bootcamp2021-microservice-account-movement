package com.everisbootcamp.accountdeposit.Controller;

import com.everisbootcamp.accountdeposit.Data.Deposit;
import com.everisbootcamp.accountdeposit.Model.DepositModel;
import com.everisbootcamp.accountdeposit.Service.DepositService;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @PostMapping("/save/{numberaccount}")
    public Mono<ResponseEntity<Map<String, Object>>> save(
        @PathVariable("numberaccount") String numberaccount,
        @RequestBody @Valid DepositModel model,
        BindingResult bindinResult
    ) {
        if (bindinResult.hasErrors()) return service.BindingResultErrors(bindinResult);
        return service
            .save(numberaccount, model)
            .map(
                response -> {
                    return ResponseEntity.status(response.getStatus()).body(response.getResponse());
                }
            )
            .defaultIfEmpty(ResponseEntity.internalServerError().build());
    }
}
