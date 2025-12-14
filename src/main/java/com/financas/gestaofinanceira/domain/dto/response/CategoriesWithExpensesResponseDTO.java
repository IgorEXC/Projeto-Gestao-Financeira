package com.financas.gestaofinanceira.domain.dto.response;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoriesWithExpensesResponseDTO {
    String categoryName;
    Integer expenseAccounting;
    List<ExpensesWithUserCategoryResponseDTO> expenses;
}
