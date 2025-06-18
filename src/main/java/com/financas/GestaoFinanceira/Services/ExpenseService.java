package com.financas.GestaoFinanceira.Services;

import com.financas.GestaoFinanceira.domain.Expense;
import com.financas.GestaoFinanceira.domain.dto.ExpenseRequestDTO;
import com.financas.GestaoFinanceira.domain.dto.ExpenseResponseDTO;
import com.financas.GestaoFinanceira.domain.mapper.ExpenseMapper;
import com.financas.GestaoFinanceira.repositories.ExpenseRepository;
import lombok.RequiredArgsConstructor;
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

	public List<ExpenseResponseDTO> findAll(){
		List<Expense> result = repository.findAll();
		return result.stream().map(mapper::entityToResponse).toList();
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

}
