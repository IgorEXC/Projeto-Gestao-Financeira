package com.financas.gestaofinanceira.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.financas.gestaofinanceira.domain.FinancialPlanning;

public interface FinancialPlanningRepository extends JpaRepository<FinancialPlanning, Long> {

}
