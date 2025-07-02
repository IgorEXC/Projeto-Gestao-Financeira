package com.financas.gestaofinanceira.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.financas.gestaofinanceira.domain.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

}
