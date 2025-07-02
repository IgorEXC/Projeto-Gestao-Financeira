package com.financas.gestaofinanceira.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.financas.gestaofinanceira.domain.Report;

public interface ReportRepository extends JpaRepository<Report, Long>{

}
