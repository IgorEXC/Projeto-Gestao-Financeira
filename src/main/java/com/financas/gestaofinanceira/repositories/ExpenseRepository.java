package com.financas.gestaofinanceira.repositories;

import com.financas.gestaofinanceira.domain.Expense;
import com.financas.gestaofinanceira.domain.dto.projections.ExpenseCategoryProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ExpenseRepository extends JpaRepository<Expense, Long>, JpaSpecificationExecutor<Expense> {

    Page<Expense> findAll(Pageable pageable);

    @Query("""
    SELECT
        exp.name AS expenseName,
        cat.name AS categoryName
        FROM Expense AS exp
        INNER JOIN ProductCategory AS cat ON exp.id = cat.id
    WHERE exp.id = :idExpense
    """)
    ExpenseCategoryProjection findCategoryByExpenseId(@Param("idExpense") Long idExpense);

    //List<Expense> getExpensesByCategoryId(Long categoryId);
}
