package com.financas.gestaofinanceira.services;

import com.financas.gestaofinanceira.domain.Category;
import com.financas.gestaofinanceira.domain.dto.CategoryRequestDTO;
import com.financas.gestaofinanceira.domain.dto.CategoryResponseDTO;
import com.financas.gestaofinanceira.domain.mapper.CategoryMapper;
import com.financas.gestaofinanceira.repositories.CategoryRepository;
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

	public Category findById(Long id) {
		return repository.findById(id).orElseThrow();
	}

	@Transactional
	public Category insert(CategoryRequestDTO category){
		Category obj = mapper.requestToEntity(category);
		return repository.save(obj);
	}
}
