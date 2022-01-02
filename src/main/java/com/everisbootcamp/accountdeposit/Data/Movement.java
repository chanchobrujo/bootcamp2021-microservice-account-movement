package com.everisbootcamp.accountdeposit.Data;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.*;
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

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime datecreated = LocalDateTime.now(ZoneId.of("America/Lima"));

    public Movement(String numberaccount, Double amount) {
        this.numberaccount = numberaccount;
        this.amount = amount;
    }
}
