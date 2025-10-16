package com.financas.gestaofinanceira.services;

import com.financas.gestaofinanceira.domain.FinancialPlanning;
import com.financas.gestaofinanceira.domain.dto.FinancialPlanningRequestDTO;
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
    private final UserService userService;

	public List<FinancialPlanningResponseDTO> findAll(){
		List<FinancialPlanning> list = repository.findAll();
		return list.stream().map(mapper::entityToResponse).toList();
	}

	public FinancialPlanningResponseDTO fingById(Long id) {
		FinancialPlanning obj = repository.findById(id).orElseThrow();
		return mapper.entityToResponse(obj);
	}

    public FinancialPlanningResponseDTO findById(Long userId, FinancialPlanningRequestDTO dto) {
        Long user = userService.findById(userId).getId();
        //cadastrar um plano financeiro para um usuario pelo id do usuario
        return null;

    }

    private Double MonthlyEconomicsGoal(Long userId, FinancialPlanningResponseDTO dto) {
        return 0.0;
    }
}
