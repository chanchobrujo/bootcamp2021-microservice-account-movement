package com.everisbootcamp.accountdeposit.Controller;

import com.everisbootcamp.accountdeposit.Error.ResponseBindingResultErrors;
import com.everisbootcamp.accountdeposit.Model.Request.RequestMovement;
import com.everisbootcamp.accountdeposit.Service.TypeMovementService;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping
public class MovmentController {

    @Autowired
    private TypeMovementService service;

    @Autowired
    private ResponseBindingResultErrors responseBindingResultErrors;

    /**
     * @GetMapping("/")
     * public Mono<ResponseEntity<Flux<Deposit>>> findByAll() {
     * return Mono.just(ResponseEntity.ok().body(service.findAll()));
     * }
     *
     * @GetMapping("/{numberaccount}")
     * public Mono<ResponseEntity<Flux<Deposit>>> findByNumberAccount(
     * @PathVariable("numberaccount") String numberaccount
     * ) {
     * return
     * Mono.just(ResponseEntity.ok().body(service.findByNumberAccount(numberaccount)));
     * }
     */

    @PostMapping("/save")
    public Mono<ResponseEntity<Map<String, Object>>> save(
            @RequestParam String numberaccount,
            @RequestBody @Valid RequestMovement model,
            BindingResult bindinResult) {
        if (bindinResult.hasErrors())
            return this.responseBindingResultErrors.BindingResultErrors(
                    bindinResult);
        return service
                .initMovement(numberaccount, model)
                .map(
                        response -> {
                            return ResponseEntity.status(response.getStatus()).body(response.getResponse());
                        })
                .defaultIfEmpty(ResponseEntity.internalServerError().build());
    }
}
