package com.financas.gestaofinanceira.domain.dto.response;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExpensesByUserCategoryResponseDTO {
    private String categoryName;
    private List<ExpenseResponseDTO> expenses;
}
