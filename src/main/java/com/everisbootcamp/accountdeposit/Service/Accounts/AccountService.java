package com.everisbootcamp.accountdeposit.Service.Accounts;

import com.everisbootcamp.accountdeposit.Connection.ConnectionMicroservicesAccount;
import com.everisbootcamp.accountdeposit.Model.Request.RequestUpdateBalance;
import com.everisbootcamp.accountdeposit.Model.Response.ResponseAccount;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private ConnectionMicroservicesAccount connectionMicroservicesAccount;

    public ResponseEntity<ResponseAccount> findAccountByNumberAccount(String number) {
        return this.connectionMicroservicesAccount.findAccountByNumberAccount(number);
    }

    public ResponseEntity<Map<String, Object>> updateBalanceAccount(RequestUpdateBalance model) {
        return this.connectionMicroservicesAccount.updateBalance(model);
    }

    public Double AmountAccount(String number) {
        ResponseAccount responseAccount = this.findAccountByNumberAccount(number).getBody();
        return responseAccount.getAmount();
    }
}
