package com.financas.gestaofinanceira.services;

import com.financas.gestaofinanceira.configuration.security.CurrentUserLogged;
import com.financas.gestaofinanceira.domain.ProductCategory;
import com.financas.gestaofinanceira.domain.dto.request.CategoryRequestDTO;
import com.financas.gestaofinanceira.domain.dto.response.CategoryResponseDTO;
import com.financas.gestaofinanceira.domain.mapper.CategoryMapper;
import com.financas.gestaofinanceira.exceptions.BusinessException;
import com.financas.gestaofinanceira.repositories.ProductCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class ProductCategoryService {

	private final ProductCategoryRepository repository;
	private final CategoryMapper mapper;
    private final ExpenseService expenseService;

	public List<CategoryResponseDTO> findAll(){
        Long userId = CurrentUserLogged.getCurrentUserId();
		List<ProductCategory> result = repository.findAllCategories(userId);
		return result.stream().map(mapper::entityToResponse).toList();
	}

	public ProductCategory findById(Long id) {
		return repository.findById(id).orElseThrow(() -> new BusinessException("Category not found!"));
	}

    public ProductCategory findProductCategoryByName(String name){
        expenseService.findAll().stream().map(exp -> ex)
    }

	@Transactional
	public ProductCategory insert(CategoryRequestDTO category){
		ProductCategory obj = mapper.requestToEntity(category);
		return repository.save(obj);
	}
}
