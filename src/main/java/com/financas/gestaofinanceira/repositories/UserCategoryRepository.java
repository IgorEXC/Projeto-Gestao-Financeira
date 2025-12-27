package com.financas.gestaofinanceira.repositories;

import com.financas.gestaofinanceira.domain.UserCategory;
import com.financas.gestaofinanceira.domain.dto.projections.ExpensesByUserCategoryProjection;
import com.financas.gestaofinanceira.domain.dto.response.UserCategoriesByUserIdResponseDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserCategoryRepository extends JpaRepository<UserCategory, Long> {

    @Query("""
        SELECT
            exp.name AS expense,
            cat.name AS category
        FROM Expense AS exp
        INNER JOIN UserCategory AS cat ON cat.id = exp.id
        INNER JOIN User AS user ON cat.id = user.id
        WHERE cat.id = :categoryId
            AND user.id = :userId
    """)
    List<ExpensesByUserCategoryProjection> getExpensesByUserCategory(
            @Param("userId") Long userId, @Param("categoryId") Long categoryId);
    //essa consulta tambem esta errada

    @Query(value = """
        SELECT
            user.name AS userName,
            user.cpf AS cpf,
            cat.name AS categoryName,
            exp.name AS expense,
            exp.description AS description,
            exp.price AS expensePrice,
            exp.dateOfPurchase AS dateOfPurchase,
            exp.necessaryExpense AS necessaryExpense
        FROM UserCategory AS cat
                INNER JOIN User AS user ON cat.user.id = user.id
                INNER JOIN cat.expenses exp
            WHERE user.id = :userId
        ORDER BY cat.name
    """)
    List<UserCategoriesByUserIdResponseDTO> getAllUserCategoriesByUserId(@Param("userId") Long userId);
    //concertar contador de colunas
}
