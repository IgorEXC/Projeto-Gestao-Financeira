package com.financas.gestaofinanceira.controller;

import com.financas.gestaofinanceira.annotations.GETMultiFormat;
import com.financas.gestaofinanceira.annotations.POSTMultiFormat;
import com.financas.gestaofinanceira.domain.ProductCategory;
import com.financas.gestaofinanceira.domain.dto.request.CategoryRequestDTO;
import com.financas.gestaofinanceira.domain.dto.response.CategoryResponseDTO;
import com.financas.gestaofinanceira.domain.mapper.CategoryMapper;
import com.financas.gestaofinanceira.services.ProductCategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1/categories")
public class CategoryController {

	private final ProductCategoryService service;
	private final CategoryMapper mapper;

	@GETMultiFormat
	public ResponseEntity<List<CategoryResponseDTO>> findAll(){
		return ResponseEntity.ok().body(service.findAll());
	}

	@GETMultiFormat(value = "/{id}")
	public ResponseEntity<CategoryResponseDTO> findById(@PathVariable Long id){
		ProductCategory productCategory = service.findById(id);
		return ResponseEntity.ok().body(mapper.entityToResponse(productCategory));
	}

	@POSTMultiFormat
	public ResponseEntity<ProductCategory> insert(@Valid @RequestBody CategoryRequestDTO dto){
		ProductCategory productCategory = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(productCategory.getId()).toUri();
		return ResponseEntity.created(uri).body(productCategory);
	}

}
