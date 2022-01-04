package com.everisbootcamp.accountdeposit.Constants.Enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public enum Details {
    CUSTOMER("Cliente que realizó la operación"),
    REASON("Razón de la operación");

    private String name;
}
