package com.financas.gestaofinanceira.services;

import com.financas.gestaofinanceira.domain.Expense;
import com.financas.gestaofinanceira.domain.dto.request.ExpenseRequestDTO;
import com.financas.gestaofinanceira.domain.dto.response.ExpenseResponseDTO;
import com.financas.gestaofinanceira.domain.dto.response.ExpenseWithCategoryResponseDTO;
import com.financas.gestaofinanceira.domain.mapper.ExpenseMapper;
import com.financas.gestaofinanceira.repositories.ExpenseRepository;
import com.financas.gestaofinanceira.domain.dto.projections.ExpenseCategoryProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class ExpenseService {

	private final ExpenseRepository repository;
	private final ExpenseMapper mapper;
	private final CategoryService categoryService;

	public Page<ExpenseResponseDTO> findAll(int page, int size) {
		List<ExpenseResponseDTO> pageResult = repository
                .findAll(PageRequest.of(page, size))
                .stream()
                .map(mapper::entityToResponse)
                .toList();
        return new PageImpl<>(pageResult, PageRequest.of(page, size), pageResult.size());
	}

	public ExpenseResponseDTO findById(Long id) {
		Expense result = repository.findById(id).orElseThrow();
		return mapper.entityToResponse(result);
	}

	@Transactional
	public Expense insert(ExpenseRequestDTO dto) {
		Expense expense = mapper.requestToEntity(dto);
		expense.setCategory(categoryService.findById(dto.getCategoryId()));
		return repository.save(expense);
	}

    public ExpenseWithCategoryResponseDTO findCategoryByExpense(Long id){
        ExpenseCategoryProjection categoryByExpenseId = repository.findCategoryByExpenseId(id);
        if(categoryByExpenseId != null){
            ExpenseWithCategoryResponseDTO dto = new ExpenseWithCategoryResponseDTO();
            dto.setCategoryName(categoryByExpenseId.getCategoryName());
            dto.setExpenseName(categoryByExpenseId.getExpenseName());
            return dto;
        }
        throw new RuntimeException("Category not found!");
    }

}
