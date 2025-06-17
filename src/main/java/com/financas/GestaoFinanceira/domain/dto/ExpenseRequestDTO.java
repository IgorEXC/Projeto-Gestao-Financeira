package com.financas.GestaoFinanceira.domain.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseRequestDTO {

    private Long id;

    @Size(max = 50)
    private String name;

    @Size(max = 100)
    private String description;

    private Double price;

    //a data esta vindo nula
    private LocalDate date;

    private Boolean necessaryExpense;
}
