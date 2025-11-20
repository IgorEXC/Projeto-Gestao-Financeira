package com.financas.gestaofinanceira.domain.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfPurchase;

    private Boolean necessaryExpense;
}
