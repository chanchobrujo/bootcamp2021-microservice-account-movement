package com.everisbootcamp.accountdeposit.Service;

import com.everisbootcamp.accountdeposit.Common.Utils;
import com.everisbootcamp.accountdeposit.Constants.Enums.Details;
import java.util.HashMap;
import java.util.Map;

public class DetailService {

    public Map<String, String> defineDetails(String client, String razon) {
        Boolean verifyClientNoEmpty = !Utils.StringEmpty(client);
        Boolean verifyRazonNoEmpty = !Utils.StringEmpty(razon);

        Map<String, String> detail = new HashMap<>();

        if (verifyClientNoEmpty) detail.put(Details.CUSTOMER.getName(), client);

        if (verifyRazonNoEmpty) detail.put(Details.REASON.getName(), razon);

        return detail;
    }
}
