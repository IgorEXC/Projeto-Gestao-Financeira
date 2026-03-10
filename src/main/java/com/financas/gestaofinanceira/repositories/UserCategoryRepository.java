package com.financas.gestaofinanceira.repositories;

import com.financas.gestaofinanceira.domain.UserCategory;
import com.financas.gestaofinanceira.domain.dto.response.UserCategoriesByUserIdProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserCategoryRepository extends JpaRepository<UserCategory, Long>, JpaSpecificationExecutor<UserCategory> {

    @Query("""
        SELECT cat.id
        FROM UserCategory AS cat
        WHERE cat.name ILIKE %:categoryName%
    """)
    Long findUserCategoryIdByCategoryName(String categoryName);

    @Query("""
        SELECT
            exp.name AS expense,
            exp.description AS description,
            exp.price AS expensePrice,
            exp.dateOfPurchase AS dateOfPurchase,
            exp.necessaryExpense AS necessaryExpense,
            cat.name AS categoryName
        FROM UserCategory AS cat
        INNER JOIN User AS user ON cat.user.id = user.id
        LEFT JOIN cat.expenses exp
        WHERE cat.id = :categoryId
            AND user.id = :userId
    """)
    List<UserCategoriesByUserIdProjection> getExpensesByUserCategory(
            @Param("userId") Long userId, @Param("categoryId") Long categoryId);

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
    List<UserCategoriesByUserIdProjection> getAllUserCategoriesByUserId(@Param("userId") Long userId);
}
