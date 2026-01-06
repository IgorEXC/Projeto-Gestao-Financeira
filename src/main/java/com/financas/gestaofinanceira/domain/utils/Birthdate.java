package com.financas.gestaofinanceira.domain.utils;

import lombok.Getter;

import java.time.LocalDate;
import java.util.Objects;

@Getter
public class Birthdate {

    private final LocalDate birthdate;

    public Birthdate (LocalDate birthdate) {
        if (!validateBirthdate(birthdate)) {
            throw new IllegalArgumentException("Birthdate is invalid!");
        }
        this.birthdate = birthdate;
    }

    private boolean validateBirthdate(LocalDate birthdate) {
        if (Objects.isNull(birthdate)) {
            return false;
        }
        return !birthdate.isBefore(LocalDate.now().minusYears(100L))
                && !birthdate.isAfter(LocalDate.now());
    }
}
