package com.financas.gestaofinanceira.services;

import com.financas.gestaofinanceira.domain.Report;
import com.financas.gestaofinanceira.domain.dto.request.ReportRequestDTO;
import com.financas.gestaofinanceira.domain.dto.response.ReportResponseDTO;
import com.financas.gestaofinanceira.domain.mapper.ReportMapper;
import com.financas.gestaofinanceira.repositories.ReportRepository;
import com.financas.gestaofinanceira.repositories.ReportRepositoryImpl;
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

	@Transactional
	public void insert(ReportRequestDTO dto){
		Report obj = mapper.requestToEntity(dto);
		obj.getUser().setId(dto.getId());
		repository.save(obj);
	}
}
