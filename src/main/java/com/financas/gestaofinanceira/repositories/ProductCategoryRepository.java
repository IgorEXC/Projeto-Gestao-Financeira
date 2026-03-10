package com.financas.gestaofinanceira.repositories;

import com.financas.gestaofinanceira.domain.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long>{

    ProductCategory findProductCategory

    @Query("""
    SELECT cat
    FROM ProductCategory AS cat
    INNER JOIN cat.expenses AS exp
    INNER JOIN exp.user AS user
    WHERE user.id = :userId
    """)
    List<ProductCategory> findAllCategories(@Param("userId") Long userId);
}
