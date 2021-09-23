package com.everisbootcamp.accountdeposit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class AccountDepositApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountDepositApplication.class, args);
        log.info("MICROSERVER DEPOSIT ENABLED");
    }
}
