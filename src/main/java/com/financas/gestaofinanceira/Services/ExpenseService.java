package com.financas.gestaofinanceira.Services;

import com.financas.gestaofinanceira.domain.Expense;
import com.financas.gestaofinanceira.domain.dto.ExpenseRequestDTO;
import com.financas.gestaofinanceira.domain.dto.ExpenseResponseDTO;
import com.financas.gestaofinanceira.domain.mapper.ExpenseMapper;
import com.financas.gestaofinanceira.repositories.ExpenseRepository;
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
