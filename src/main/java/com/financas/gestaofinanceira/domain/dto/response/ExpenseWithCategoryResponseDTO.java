package com.financas.gestaofinanceira.domain.dto.response;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseWithCategoryResponseDTO {
    private String expenseName;
    private String categoryName;
}
