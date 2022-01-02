package com.everisbootcamp.accountdeposit.Model.Request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
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
public class RequestMovement {

    @NotBlank(message = "El campo tipo de movimiento, no debe estar vacio.")
    private String typemovement;

    @Min(10)
    private Double amount;
}
