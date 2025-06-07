package com.financas.GestaoFinanceira.resources;

import com.financas.GestaoFinanceira.Services.ExpenseService;
import com.financas.GestaoFinanceira.domain.dto.ExpenseResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/expenses") 
public class ExpenseResource {

	private final ExpenseService service;
	
	@GetMapping
	public List<ExpenseResponseDTO> findAll(){
		return service.findAll();
	}
	
	@GetMapping(value = "/{id}")
	public ExpenseResponseDTO findById(@PathVariable Long id){
		return service.findById(id);
	}

}
