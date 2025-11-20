package com.financas.gestaofinanceira.domain.dto.projections;

import com.financas.gestaofinanceira.domain.dto.response.ExpenseResponseDTO;


public interface ExpensesByUserCategoryProjection {
    String getCategoryName();
    String getUserName();
    String getExpense();
}
