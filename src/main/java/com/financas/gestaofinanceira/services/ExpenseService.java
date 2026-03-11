package com.financas.gestaofinanceira.services;

import com.financas.gestaofinanceira.configuration.security.CurrentUserLogged;
import com.financas.gestaofinanceira.domain.Expense;
import com.financas.gestaofinanceira.domain.User;
import com.financas.gestaofinanceira.domain.dto.projections.ExpenseCategoryProjection;
import com.financas.gestaofinanceira.domain.dto.request.ExpenseRequestDTO;
import com.financas.gestaofinanceira.domain.dto.request.RangeDateRequestDTO;
import com.financas.gestaofinanceira.domain.dto.response.ExpenseResponseDTO;
import com.financas.gestaofinanceira.domain.dto.response.ExpenseWithCategoryResponseDTO;
import com.financas.gestaofinanceira.domain.mapper.ExpenseMapper;
import com.financas.gestaofinanceira.repositories.ExpenseRepository;
import com.financas.gestaofinanceira.repositories.impl.ExpenseDynamicQueryRepository;
import com.financas.gestaofinanceira.repositories.utils.BaseSpecs;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Description;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class ExpenseService implements BaseSpecs<Expense> {

	private final ExpenseRepository repository;
	private final ExpenseMapper mapper;
    private final UserService userService;
    private final ExpenseDynamicQueryRepository dynamicQueryRepository;

	public Page<ExpenseResponseDTO> findAll(int page, int size) {
		List<ExpenseResponseDTO> pageResult = repository
                .findAll(PageRequest.of(page, size))
                .stream()
                .filter(expense -> expense.getUser().getId().equals(CurrentUserLogged.getCurrentUserId()))
                .map(mapper::entityToResponse)
                .toList();
        return new PageImpl<>(pageResult, PageRequest.of(page, size), pageResult.size());
	}

    @Description("Usado para retornar os dados para o Refine UI - paliativo até concertar o método Provider")
    public List<ExpenseResponseDTO> findAll() {
		return repository
                .findAll()
                .stream()
                .filter(expense -> expense.getUser().getId().equals(CurrentUserLogged.getCurrentUserId()))
                .map(mapper::entityToResponse)
                .sorted(Comparator.comparing(ExpenseResponseDTO::getDateOfPurchase).reversed())
                .toList();
	}

    //Intervalo de datas inicial e final não estão filtrando corretamente
    public List<ExpenseResponseDTO> findAllByPurchaseDate(RangeDateRequestDTO dto) {
        return repository.findAll(dynamicQueryRepository
                .findExpensesByRangeDate(dto.startDate(), dto.endDate(), getUserLogged()))
                .stream()
                .map(mapper::entityToResponse)
                .toList();
    }

	public ExpenseResponseDTO findById(Long id) {
		Expense result = repository.findById(id).orElseThrow();
		return mapper.entityToResponse(result);
	}

	@Transactional
	public Expense insert(ExpenseRequestDTO dto) {
		Expense expense = mapper.requestToEntity(dto);
        //provavelmente para salvar um expense você tera que definir sua categoria e a
        //categoria de usuario (opcional). Categoria de usuario pode ser adicionada depois com
        //uma busca por nome da categoria de usuario.
		//expense.setProductCategory(categoryService.findById(dto.getCategoryId()));
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

    private User getUserLogged(){
        return userService.findById(CurrentUserLogged.getCurrentUserId());
    }
}
