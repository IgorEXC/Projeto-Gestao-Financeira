package com.financas.gestaofinanceira.domain.utils;

import java.time.LocalDate;
import java.util.Objects;

public record Birthdate(LocalDate birthdate) {

    public Birthdate {
        if (!validateBirthdate(birthdate)) {
            throw new IllegalArgumentException("Birthdate is invalid!");
        }
    }

    private boolean validateBirthdate(LocalDate birthdate) {
        if (Objects.isNull(birthdate)) {
            return false;
        }
        return !birthdate.isBefore(LocalDate.now().minusYears(100L))
                && !birthdate.isAfter(LocalDate.now());
    }
}
