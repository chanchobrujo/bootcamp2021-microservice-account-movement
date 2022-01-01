package com.everisbootcamp.accountdeposit.Connection;

import com.everisbootcamp.accountdeposit.Constants.Paths.Path;
import org.springframework.web.reactive.function.client.WebClient;

public class Consumer {

    public static final WebClient webClientCustomer = WebClient.create(Path.URLS.CUSTOMERS_PATH);
    public static final WebClient webClientLogic = WebClient.create(Path.URLS.LOGIC_PATH);
}
