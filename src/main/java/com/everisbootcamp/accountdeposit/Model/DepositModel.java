package com.everisbootcamp.accountdeposit.Model;

import javax.validation.constraints.Min; 
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class DepositModel {
    @Min(10)
    private Integer amount; 
}
