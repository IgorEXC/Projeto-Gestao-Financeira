package com.financas.gestaofinanceira.domain.dto.projections;

import jakarta.annotation.Nullable;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface ExpensesByUserCategoryProjection {
        String getCategory();
        String getExpense();
        @Nullable String getDescription();
        BigDecimal getExpensePrice();
        @Nullable LocalDate getDateOfPurchase();
        @Nullable Boolean getNecessaryExpense();
}
