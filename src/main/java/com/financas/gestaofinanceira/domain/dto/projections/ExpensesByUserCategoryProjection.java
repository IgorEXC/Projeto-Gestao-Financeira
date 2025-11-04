package com.financas.gestaofinanceira.domain.dto.projections;

import com.financas.gestaofinanceira.domain.dto.response.ExpenseResponseDTO;

import java.util.List;

public interface ExpensesByUserCategoryProjection {
    String getCategoryName();
    List<ExpenseResponseDTO> getExpenses();
}
