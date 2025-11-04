package com.financas.gestaofinanceira.repositories;

import com.financas.gestaofinanceira.domain.dto.projections.ExpensesByUserCategoryProjection;
import org.springframework.data.jpa.repository.JpaRepository;

import com.financas.gestaofinanceira.domain.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategoryRepository extends JpaRepository<Category, Long>{

    //implementar method que retorna todas as despesas de determinada categoria por usuario

    @Query("""
        SELECT 
            exp.name AS expenses,
            exp.category.name AS categoryName    
        FROM Expense AS exp
        INNER JOIN UserExpense AS user_exp ON user_exp.id.expense.id = exp.id
        INNER JOIN User AS user ON user.id = user_exp.id.user.id
        WHERE exp.category.id = :categoryId
            AND user.id = :userId
    """)
    ExpensesByUserCategoryProjection getExpensesByUserCategory(
            @Param("userId") Long userId, @Param("categoryId") Long categoryId);
}
