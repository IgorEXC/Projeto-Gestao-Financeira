package com.financas.gestaofinanceira.repositories;

import com.financas.gestaofinanceira.domain.FinancialPlanning;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FinancialPlanningRepository extends JpaRepository<FinancialPlanning, Long> {

}
