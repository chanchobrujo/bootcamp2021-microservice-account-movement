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

    @Min(1)
    private Double amount;

    private String dni = "";
    private String nombre = "";
    private String apellido = "";

    private String razon = "";
}
