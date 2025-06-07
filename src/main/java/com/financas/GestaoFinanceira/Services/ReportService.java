package com.financas.GestaoFinanceira.Services;

import com.financas.GestaoFinanceira.domain.Report;
import com.financas.GestaoFinanceira.domain.dto.ReportResponseDTO;
import com.financas.GestaoFinanceira.domain.mapper.ReportMapper;
import com.financas.GestaoFinanceira.repositories.ReportRepository;
import com.financas.GestaoFinanceira.repositories.ReportRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class ReportService {

	private final ReportRepository repository;
	private final ReportRepositoryImpl repositoryImpl;
	private final ReportMapper mapper;

	public List<ReportResponseDTO> findAll() {
		List<Report> list = repository.findAll();
        return list.stream().map(mapper::entityToResponse).toList();
	}

	public ReportResponseDTO fingById(Long id) {
		Report obj = repository.findById(id).orElseThrow();
		return mapper.entityToResponse(obj);
	}

	public Report findByUserCpf(String cpf) {
		return repositoryImpl.findUserCpf(cpf);
	}
}
