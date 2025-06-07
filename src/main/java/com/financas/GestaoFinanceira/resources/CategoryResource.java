package com.financas.GestaoFinanceira.resources;

import com.financas.GestaoFinanceira.Services.CategoryService;
import com.financas.GestaoFinanceira.domain.dto.CategoryResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/categories") 
public class CategoryResource {

	private final CategoryService service;
	
	@GetMapping
	public List<CategoryResponseDTO> findAll(){
        return service.findAll();
	}
	
	@GetMapping(value = "/{id}")
	public CategoryResponseDTO findById(@PathVariable Long id){
        return service.findById(id);
	}
}
