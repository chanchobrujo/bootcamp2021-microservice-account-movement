package com.everisbootcamp.accountdeposit.Constants.Enums.Types;

import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TypeMovement {
    DEPOSIT("D", "Depósito"),
    RETIRE("R", "Retiro");

    private String name;
    private String secondName;

    public static Optional<TypeMovement> FindByName(String name) {
        for (TypeMovement type : values()) {
            Boolean verify = type.getName().toUpperCase().equals(name.toUpperCase());
            if (verify) return Optional.of(type);
        }
        return Optional.empty();
    }
}
