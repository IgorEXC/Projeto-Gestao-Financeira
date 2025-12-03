package com.financas.gestaofinanceira.domain.dto.projections;

public interface ExpensesByUserCategoryProjection {
    String getCategoryName();
    String getUserName();
    String getExpense();
}
