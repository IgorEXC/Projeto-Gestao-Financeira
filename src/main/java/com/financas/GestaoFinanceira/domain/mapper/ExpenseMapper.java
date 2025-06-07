package com.financas.GestaoFinanceira.domain.mapper;

import com.financas.GestaoFinanceira.domain.Expense;
import com.financas.GestaoFinanceira.domain.dto.ExpenseRequestDTO;
import com.financas.GestaoFinanceira.domain.dto.ExpenseResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExpenseMapper {

    Expense requestToEntity(ExpenseRequestDTO dto);

    ExpenseRequestDTO entityToRequest(Expense entity);

    Expense responseToEntity(ExpenseResponseDTO dto);

    ExpenseResponseDTO entityToResponse(Expense entity);
}
