package com.everisbootcamp.accountdeposit.Service;

import com.everisbootcamp.accountdeposit.Connection.ConnectionMicroservicesAccount;
import com.everisbootcamp.accountdeposit.Model.Request.RequestUpdateBalance;
import com.everisbootcamp.accountdeposit.Model.Response.ResponseAccount;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

public class AccountService {

    @Autowired
    private ConnectionMicroservicesAccount connectionMicroservicesAccount;

    public ResponseAccount findAccountByNumberAccount(String number) {
        return this.connectionMicroservicesAccount.findAccountByNumberAccount(number).getBody();
    }

    public ResponseEntity<Map<String, Object>> updateBalanceAccount(RequestUpdateBalance model) {
        // return this.connectionMicroservicesAccount.updateBalance(number);
        return null;
    }

    public Double AmountAccount(String number) {
        ResponseAccount responseAccount = this.findAccountByNumberAccount(number);
        return responseAccount.getAmount();
    }
}
