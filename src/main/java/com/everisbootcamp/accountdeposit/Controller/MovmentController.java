package com.everisbootcamp.accountdeposit.Controller;

import com.everisbootcamp.accountdeposit.Error.ResponseBindingResultErrors;
import com.everisbootcamp.accountdeposit.Model.Request.RequestMovement;
import com.everisbootcamp.accountdeposit.Model.Response.ResponseMovement;
import com.everisbootcamp.accountdeposit.Service.ResponseMovementsService;
import com.everisbootcamp.accountdeposit.Service.TypeMovementService;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping
public class MovmentController {

    @Autowired
    private TypeMovementService typeMovementService;

    @Autowired
    private ResponseMovementsService responseMovementsService;

    @Autowired
    private ResponseBindingResultErrors responseBindingResultErrors;

    @GetMapping("/")
    public Mono<ResponseEntity<Flux<ResponseMovement>>> findByAll() {
        return Mono.just(ResponseEntity.ok().body(this.responseMovementsService.findAll()));
    }

    @GetMapping("/findByNumber")
    public Mono<ResponseEntity<Flux<ResponseMovement>>> findByNumberAccount(
        @RequestParam String numberaccount
    ) {
        return Mono.just(
            ResponseEntity
                .ok()
                .body(this.responseMovementsService.findAllByNumberAccount(numberaccount))
        );
    }

    @PostMapping("/save")
    public Mono<ResponseEntity<Map<String, Object>>> save(
        @RequestParam String numberaccount,
        @RequestBody @Valid RequestMovement model,
        BindingResult bindinResult
    ) {
        if (bindinResult.hasErrors()) return this.responseBindingResultErrors.BindingResultErrors(
                bindinResult
            );
        return typeMovementService
            .initMovement(numberaccount, model)
            .map(
                response -> {
                    return ResponseEntity.status(response.getStatus()).body(response.getResponse());
                }
            )
            .defaultIfEmpty(ResponseEntity.internalServerError().build());
    }
}
