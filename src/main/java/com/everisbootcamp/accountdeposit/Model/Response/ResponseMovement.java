package com.everisbootcamp.accountdeposit.Model.Response;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Builder
@AllArgsConstructor
public class ResponseMovement {

    private String type;
    private String numberaccount;
    private Double amount;

    private String datecreated;
    private Map<String, String> details;
}
