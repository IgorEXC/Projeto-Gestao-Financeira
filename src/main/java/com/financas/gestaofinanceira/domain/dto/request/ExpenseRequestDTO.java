package com.financas.gestaofinanceira.domain.dto.request;

import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

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

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateOfPurchase;

    private Boolean necessaryExpense;

    private Long categoryId;
}
