package com.everisbootcamp.accountdeposit.Data;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Builder
@AllArgsConstructor
@Document(collection = "movement")
public class Movement {

    @Id
    private String iddeposit;

    private String typemovement;
    private String numberaccount;
    private Double amount;

    private String datecreated;

    public Movement(String numberaccount, Double amount) {
        this.numberaccount = numberaccount;
        this.amount = amount;
    }
}
