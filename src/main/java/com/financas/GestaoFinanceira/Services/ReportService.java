package com.financas.GestaoFinanceira.Services;

import java.util.List;

import com.financas.GestaoFinanceira.repositories.ReportRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.financas.GestaoFinanceira.domain.Report;

import com.financas.GestaoFinanceira.domain.dto.ReportDTO;
import com.financas.GestaoFinanceira.repositories.ReportRepository;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class ReportService {

	private final ReportRepository repository;
	private final ReportRepositoryImpl repositoryImpl;

	public List<ReportDTO> findAll() {
		List<Report> list = repository.findAll();
        return list.stream().map(ReportDTO::new).toList();
	}

	public ReportDTO fingById(Long id) {
		Report obj = repository.findById(id).orElseThrow();
		return new ReportDTO(obj);
	}

	public Report findByUserCpf(String cpf) {
		return repositoryImpl.findUserCpf(cpf);
	}
}
