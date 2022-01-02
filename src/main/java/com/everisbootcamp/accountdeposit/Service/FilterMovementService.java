package com.everisbootcamp.accountdeposit.Service;

import com.everisbootcamp.accountdeposit.Interface.DepositRepository;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;

public class FilterMovementService {

    @Autowired
    private DepositRepository repository;

    public int getMonthlyMovementsQuantity(String numberaccount) {
        Date date = new Date();
        Integer month = date.getMonth();
        return (int) repository
            .findAll()
            .toStream()
            .filter(mov -> mov.getDatecreated().getMonth().getValue() == month)
            .filter(d -> d.getNumberaccount().equals(numberaccount))
            .count();
    }
}
