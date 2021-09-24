package com.everisbootcamp.accountdeposit.Web;

import com.everisbootcamp.accountdeposit.Constants.Constants;
import org.springframework.web.reactive.function.client.WebClient;

public class Consumer {

    public static final WebClient webclientAccount = WebClient.create(
        Constants.Path.ACCOUNT_PATH
    );
}
