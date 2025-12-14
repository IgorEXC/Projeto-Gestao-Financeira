package com.financas.gestaofinanceira.domain.dto.response;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoriesWithExpensesByUserResponseDTO implements Serializable {
    String name;
    String cpf;
    List<CategoriesWithExpensesResponseDTO> expenses;
}


