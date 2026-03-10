package com.financas.gestaofinanceira.repositories.criteria;

import com.financas.gestaofinanceira.domain.Expense;
import org.springframework.data.jpa.domain.Specification;


public interface ExpenseDynamicQueryRepository {
   Specification<Expense> findExpensesByRangeDate(String startDate, String endDate);
}
