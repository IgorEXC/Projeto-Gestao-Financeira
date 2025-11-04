package com.financas.gestaofinanceira.repositories;

import com.financas.gestaofinanceira.domain.dto.projections.ExpensesByUserCategoryProjection;
import org.springframework.data.jpa.repository.JpaRepository;

import com.financas.gestaofinanceira.domain.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long>{

    //implementar method que retorna todas as despesas de determinada categoria por usuario
    //Esta retornando apenas as categorias do usuario e não as despesas por categoria filtrada
    @Query("""
        SELECT 
            exp.name AS expenses,
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
//SELECT
//    exp.name AS expenses,
//    cat.category_name AS categoryName,
//    userTB.name
//FROM tb_expense AS exp
//         INNER JOIN tb_user_expense AS user_exp ON user_exp.expense_id = exp.id
//         INNER JOIN tb_user AS userTB ON userTB.user_id = user_exp.user_id
//         INNER JOIN tb_category AS cat ON cat.id = exp.category_id
//WHERE exp.category_id = :categoryId
//  AND userTB.user_id = :userId