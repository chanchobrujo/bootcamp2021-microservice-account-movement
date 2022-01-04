package com.everisbootcamp.accountdeposit.Connection;

import com.everisbootcamp.accountdeposit.Constants.Paths.Path;
import org.springframework.web.reactive.function.client.WebClient;

public class Consumer {

    public static final WebClient webClientAccount = WebClient.create(Path.URLS.ACCOUNT_PATH);
}
