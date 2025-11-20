package com.financas.gestaofinanceira.repositories;

import com.financas.gestaofinanceira.domain.dto.projections.ExpensesByUserCategoryProjection;
import org.springframework.data.jpa.repository.JpaRepository;

import com.financas.gestaofinanceira.domain.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long>{

    @Query("""
        SELECT 
            exp.name AS expense,
            exp.category.name AS categoryName,
            user.name AS userName       
        FROM Expense AS exp
        INNER JOIN UserExpense AS user_exp ON user_exp.id.expense.id = exp.id
        INNER JOIN User AS user ON user.id = user_exp.id.user.id
        INNER JOIN Category AS cat ON cat.id = exp.category.id   
        WHERE exp.category.id = :categoryId
            AND user.id = :userId
    """)
    List<ExpensesByUserCategoryProjection> getExpensesByUserCategory(
            @Param("userId") Long userId, @Param("categoryId") Long categoryId);
}
