package com.financas.gestaofinanceira.resources;

import com.financas.gestaofinanceira.annotations.GETMultiFormat;
import com.financas.gestaofinanceira.services.FinancialPlanningService;
import com.financas.gestaofinanceira.domain.dto.FinancialPlanningResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/financial_plans")
public class FinancialPlanningResource {

	private final FinancialPlanningService service;

    @GETMultiFormat
	public List<FinancialPlanningResponseDTO> findAll(){
		return service.findAll();
	}

	@GETMultiFormat(value = "/{id}")
	public FinancialPlanningResponseDTO findById(@PathVariable Long id){
		return service.fingById(id);
	}
}
