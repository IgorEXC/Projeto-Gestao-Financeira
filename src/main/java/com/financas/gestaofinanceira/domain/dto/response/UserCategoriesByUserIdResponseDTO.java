package com.financas.gestaofinanceira.domain.dto.response;

import jakarta.annotation.Nullable;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface UserCategoriesByUserIdResponseDTO{
        String getUserName();
        String getCpf();
        String getCategoryName();
        String getExpense();
        @Nullable String getDescription();
        BigDecimal getExpensePrice();
        @Nullable LocalDate getDateOfPurchase();
        @Nullable Boolean getNecessaryExpense();
        @Nullable Integer getExpenseAccounting();
}
