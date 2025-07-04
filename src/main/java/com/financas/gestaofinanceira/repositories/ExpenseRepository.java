package com.financas.gestaofinanceira.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.financas.gestaofinanceira.domain.Expense;

public interface ExpenseRepository extends JpaRepository<Expense, Long>{

}
