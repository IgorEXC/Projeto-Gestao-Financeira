package com.financas.gestaofinanceira.services;

import com.financas.gestaofinanceira.domain.ProductCategory;
import com.financas.gestaofinanceira.domain.dto.request.CategoryRequestDTO;
import com.financas.gestaofinanceira.domain.dto.response.CategoryResponseDTO;
import com.financas.gestaofinanceira.domain.dto.response.ExpensesByUserCategoryResponseDTO;
import com.financas.gestaofinanceira.domain.mapper.CategoryMapper;
import com.financas.gestaofinanceira.exceptions.BusinessException;
import com.financas.gestaofinanceira.repositories.ProductCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class ProductCategoryService {

	private final ProductCategoryRepository repository;
	private final CategoryMapper mapper;
    private final UserService userService;

	public List<CategoryResponseDTO> findAll(){
		List<ProductCategory> result = repository.findAll();
		return result.stream().map(mapper::entityToResponse).toList();
	}

	public ProductCategory findById(Long id) {
		return repository.findById(id).orElseThrow();
	}

	@Transactional
	public ProductCategory insert(CategoryRequestDTO category){
		ProductCategory obj = mapper.requestToEntity(category);
		return repository.save(obj);
	}

    public List<ExpensesByUserCategoryResponseDTO> expensesByUserCategoryResponseDTO(Long userId, Long categoryId){
        if (!repository.existsById(categoryId) && ObjectUtils.isEmpty(userService.findById(userId))){
            throw new BusinessException("User or Category not found!");
        }
//        List<ExpensesByUserCategoryProjection> result = repository.getExpensesByUserCategory(userId, categoryId);
//        return result.stream().map(projection -> {
//            ExpensesByUserCategoryResponseDTO dto = new ExpensesByUserCategoryResponseDTO();
//            dto.setCategoryName(projection.getCategoryName());
//            dto.setUserName(projection.getUserName());
//            dto.setExpense(projection.getExpense());
//            return dto;
//        }).toList();
        return Collections.emptyList();
    }

}
