package com.financas.GestaoFinanceira.domain.dto;

import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseResponseDTO {

    private Long id;

    @Size(max = 100)
    private String description;

    private Double price;

    private LocalDate date;

    private Boolean necessaryExpense;
}
