package com.financas.gestaofinanceira.domain.dto.response;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoriesWithExpensesResponseDTO {
    String categoryName;
    Integer qtdExpenses;
    BigDecimal totalPrice;
    List<ExpensesWithUserCategoryResponseDTO> expenses;
}
