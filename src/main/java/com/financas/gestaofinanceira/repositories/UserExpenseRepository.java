package com.financas.gestaofinanceira.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.financas.gestaofinanceira.domain.UserExpense;
import com.financas.gestaofinanceira.domain.pk.UserExpensePK;

public interface UserExpenseRepository extends JpaRepository<UserExpense, UserExpensePK> {

}
