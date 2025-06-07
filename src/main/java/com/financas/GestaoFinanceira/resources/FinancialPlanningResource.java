package com.financas.GestaoFinanceira.resources;

import com.financas.GestaoFinanceira.Services.FinancialPlanningService;
import com.financas.GestaoFinanceira.domain.dto.FinancialPlanningResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/financial_plans")
public class FinancialPlanningResource {

	private final FinancialPlanningService service;
	
	@GetMapping
	public List<FinancialPlanningResponseDTO> findAll(){
		return service.findAll();
	}
	
	@GetMapping(value = "/{id}")
	public FinancialPlanningResponseDTO findById(@PathVariable Long id){
		return service.fingById(id);
	}
}
