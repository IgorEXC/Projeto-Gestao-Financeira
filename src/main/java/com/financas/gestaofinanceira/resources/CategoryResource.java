package com.financas.gestaofinanceira.resources;

import com.financas.gestaofinanceira.annotations.GETMultiFormat;
import com.financas.gestaofinanceira.annotations.POSTMultiFormat;
import com.financas.gestaofinanceira.domain.dto.response.ExpensesByUserCategoryResponseDTO;
import com.financas.gestaofinanceira.services.CategoryService;
import com.financas.gestaofinanceira.domain.Category;
import com.financas.gestaofinanceira.domain.dto.request.CategoryRequestDTO;
import com.financas.gestaofinanceira.domain.dto.response.CategoryResponseDTO;
import com.financas.gestaofinanceira.domain.mapper.CategoryMapper;
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
public class CategoryResource {

	private final CategoryService service;
	private final CategoryMapper mapper;

	@GETMultiFormat
	public ResponseEntity<List<CategoryResponseDTO>> findAll(){
		return ResponseEntity.ok().body(service.findAll());
	}

	@GETMultiFormat(value = "/{id}")
	public ResponseEntity<CategoryResponseDTO> findById(@PathVariable Long id){
		Category category = service.findById(id);
		return ResponseEntity.ok().body(mapper.entityToResponse(category));
	}

	@POSTMultiFormat
	public ResponseEntity<Category> insert(@Valid @RequestBody CategoryRequestDTO dto){
		Category category = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(category.getId()).toUri();
		return ResponseEntity.created(uri).body(category);
	}

    @GETMultiFormat(value = "/expenses-by-user-category")
    public ResponseEntity<List<ExpensesByUserCategoryResponseDTO>> expensesByUserCategory(
            @RequestParam(name = "user_id") Long userId,
            @RequestParam(name = "category_id") Long categoryId){
        return ResponseEntity.ok().body(service.expensesByUserCategoryResponseDTO(userId, categoryId));
    }
}
