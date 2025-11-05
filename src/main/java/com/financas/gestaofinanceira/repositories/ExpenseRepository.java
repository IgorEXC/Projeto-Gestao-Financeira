package com.financas.gestaofinanceira.repositories;

import com.financas.gestaofinanceira.domain.dto.projections.ExpenseCategoryProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import com.financas.gestaofinanceira.domain.Expense;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.data.domain.Pageable;

public interface ExpenseRepository extends JpaRepository<Expense, Long>{

    Page<Expense> findAll(Pageable pageable);

    @Query("""
    SELECT 
        exp.name AS expenseName, 
        cat.name AS categoryName   
        FROM Expense AS exp
        INNER JOIN Category AS cat ON exp.category.id = cat.id
    WHERE exp.id = :idExpense           
    """)
    ExpenseCategoryProjection findCategoryByExpenseId(@Param("idExpense") Long idExpense);
}
