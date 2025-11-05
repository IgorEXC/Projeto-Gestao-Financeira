package com.financas.gestaofinanceira.domain.dto.response;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExpensesByUserCategoryResponseDTO {
    private String categoryName;
    private String userName;
    private String expense;
}
