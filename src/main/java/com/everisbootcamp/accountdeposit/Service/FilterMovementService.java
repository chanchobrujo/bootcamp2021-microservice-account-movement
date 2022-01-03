package com.everisbootcamp.accountdeposit.Service;

import com.everisbootcamp.accountdeposit.Common.Utils;
import com.everisbootcamp.accountdeposit.Interface.MovementRepository;
import java.text.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FilterMovementService {

    @Autowired
    private MovementRepository repository;

    private Integer getMonth(String date) {
        try {
            return Utils.getMonth(date);
        } catch (ParseException e) {
            return -1;
        }
    }

    public int getMonthlyMovementsQuantity(String numberaccount) {
        Integer month = this.getMonth(Utils.date());

        return (int) repository
            .findAll()
            .toStream()
            .filter(mov -> this.getMonth(mov.getDatecreated()) == month)
            .filter(d -> d.getNumberaccount().equals(numberaccount))
            .count();
    }
}
