package com.everisbootcamp.accountdeposit.Service.Accounts;

import com.everisbootcamp.accountdeposit.Common.Utils;
import com.everisbootcamp.accountdeposit.Constants.Enums.RuleName;
import com.everisbootcamp.accountdeposit.Service.FilterMovementService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RuleService {

    @Autowired
    private AccountService accountService;

    @Autowired
    private FilterMovementService filterMovementService;

    private Map<String, Object> defineRulesByAccount(Map<String, Object> rules) {
        String commissionMaintenanceName = RuleName.COMMISSIONMAINTENANCE.getName();
        String maxLimitMovMonthlyName = RuleName.MAXLIMITMOVMONTHLY.getName();

        String commissionMaintenance = rules.get(commissionMaintenanceName).toString();
        String maxLimitMovMonthly = rules.get(maxLimitMovMonthlyName).toString();

        Boolean cmValue = Utils.StringToBoolean(commissionMaintenance);
        Boolean mmValue = Utils.StringToBoolean(maxLimitMovMonthly);

        rules.put(commissionMaintenanceName, cmValue);
        rules.put(maxLimitMovMonthlyName, mmValue);
        return rules;
    }

    public Boolean verifyRules(String number) {
        Map<String, Object> rules =
            this.accountService.findAccountByNumberAccount(number).getBody().getRules();
        this.defineRulesByAccount(rules);

        String maxLimitMovMonthlyName = RuleName.MAXLIMITMOVMONTHLY.getName();
        String maxLimitMovMonthlyNumberName = RuleName.MAXLIMITMOVMONTHLYNUMBER.getName();

        Boolean maxLimitMovMonthly = Utils.ObjectToBoolean(rules.get(maxLimitMovMonthlyName));
        if (maxLimitMovMonthly) {
            Integer max = Integer.parseInt(rules.get(maxLimitMovMonthlyNumberName).toString());
            Boolean value =
                maxLimitMovMonthly &&
                max == this.filterMovementService.getMonthlyMovementsQuantity(number);
            return value;
        }

        return maxLimitMovMonthly;
    }
}
