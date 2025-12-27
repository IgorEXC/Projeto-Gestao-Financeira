package com.financas.gestaofinanceira.domain.dto.response;

import lombok.*;
import org.apache.commons.lang3.ObjectUtils;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExpensesWithUserCategoryResponseDTO {
    private String name;
    private String description;
    private LocalDate dateOfPurchase;
    private Boolean necessaryExpense;
    private BigDecimal price;
}
