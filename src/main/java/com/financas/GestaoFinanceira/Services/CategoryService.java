package com.financas.GestaoFinanceira.Services;

import com.financas.GestaoFinanceira.domain.Category;
import com.financas.GestaoFinanceira.domain.dto.CategoryRequestDTO;
import com.financas.GestaoFinanceira.domain.dto.CategoryResponseDTO;
import com.financas.GestaoFinanceira.domain.mapper.CategoryMapper;
import com.financas.GestaoFinanceira.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class CategoryService {

	private final CategoryRepository repository;
	private final CategoryMapper mapper;

	public List<CategoryResponseDTO> findAll(){
		List<Category> result = repository.findAll();
		return result.stream().map(mapper::entityToResponse).toList();
	}

	public CategoryResponseDTO findById(Long id) {
		Category result = repository.findById(id).orElseThrow();
		return mapper.entityToResponse(result);
	}

	@Transactional
	public Category insert(CategoryRequestDTO category){
		Category obj = mapper.requestToEntity(category);
		return repository.save(obj);
	}
}
