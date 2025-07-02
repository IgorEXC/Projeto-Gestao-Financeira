package com.financas.gestaofinanceira.Services;

import com.financas.gestaofinanceira.domain.FinancialPlanning;
import com.financas.gestaofinanceira.domain.dto.FinancialPlanningResponseDTO;
import com.financas.gestaofinanceira.domain.mapper.FinancialPlanningMapper;
import com.financas.gestaofinanceira.repositories.FinancialPlanningRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class FinancialPlanningService {

	private final FinancialPlanningRepository repository;
	private final FinancialPlanningMapper mapper;

	public List<FinancialPlanningResponseDTO> findAll(){
		List<FinancialPlanning> list = repository.findAll();
		return list.stream().map(mapper::entityToResponse).toList();
	}

	public FinancialPlanningResponseDTO fingById(Long id) {
		FinancialPlanning obj = repository.findById(id).orElseThrow();
		return mapper.entityToResponse(obj);
	}
}
