package com.everisbootcamp.accountdeposit.Constants.Enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public enum RuleName {
    CUSTOMERTYPE("Tipo de cliente"),
    COMMISSIONMAINTENANCE("Comisión por mantenimiento"),
    MAXLIMITMOVMONTHLY("Limite de movimiento por mes"),
    MAXLIMITMOVMONTHLYNUMBER("Movimientos por mes"),
    PROFILE("Perfil de cuenta");

    private String name;
}
