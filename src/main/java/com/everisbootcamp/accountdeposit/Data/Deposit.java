package com.everisbootcamp.accountdeposit.Data;

import java.time.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Document(collection = "deposit")
public class Deposit {
    @Id
    private String idretire;
    
    private String numberaccount;
    private Integer amount;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime datecreated = LocalDateTime.now(ZoneId.of("America/Lima"));

    public Deposit(String numberaccount, Integer amount) {
        this.numberaccount = numberaccount;
        this.amount = amount;
    }
}
