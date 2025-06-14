package com.financas.GestaoFinanceira.resources;

import com.financas.GestaoFinanceira.Services.CategoryService;
import com.financas.GestaoFinanceira.domain.Category;
import com.financas.GestaoFinanceira.domain.dto.CategoryRequestDTO;
import com.financas.GestaoFinanceira.domain.dto.CategoryResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/categories") 
public class CategoryResource {

	private final CategoryService service;
	
	@GetMapping
	public ResponseEntity<List<CategoryResponseDTO>> findAll(){
		return ResponseEntity.ok().body(service.findAll());
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<CategoryResponseDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok().body(service.findById(id));
	}

	@PostMapping
	public ResponseEntity<Category> insert(@Valid @RequestBody CategoryRequestDTO dto){
		Category category = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(category.getId()).toUri();
		return ResponseEntity.created(uri).body(category);
	}
}
