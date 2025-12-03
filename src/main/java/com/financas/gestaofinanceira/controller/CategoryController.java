package com.financas.gestaofinanceira.controller;

import com.financas.gestaofinanceira.annotations.GETMultiFormat;
import com.financas.gestaofinanceira.annotations.POSTMultiFormat;
import com.financas.gestaofinanceira.domain.ProductCategory;
import com.financas.gestaofinanceira.domain.dto.request.CategoryRequestDTO;
import com.financas.gestaofinanceira.domain.dto.response.CategoryResponseDTO;
import com.financas.gestaofinanceira.domain.dto.response.ExpensesByUserCategoryResponseDTO;
import com.financas.gestaofinanceira.domain.mapper.CategoryMapper;
import com.financas.gestaofinanceira.services.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/categories")
public class CategoryController {

	private final CategoryService service;
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

    @GETMultiFormat(value = "/expenses-by-user-category")
    public ResponseEntity<List<ExpensesByUserCategoryResponseDTO>> expensesByUserCategory(
            @RequestParam(name = "user_id") Long userId,
            @RequestParam(name = "category_id") Long categoryId){
        return ResponseEntity.ok().body(service.expensesByUserCategoryResponseDTO(userId, categoryId));
    }
}
