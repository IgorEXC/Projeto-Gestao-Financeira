package com.financas.gestaofinanceira.domain.mapper;

import com.financas.gestaofinanceira.domain.Expense;
import com.financas.gestaofinanceira.domain.dto.request.ExpenseRequestDTO;
import com.financas.gestaofinanceira.domain.dto.response.ExpenseResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExpenseMapper {

    Expense requestToEntity(ExpenseRequestDTO dto);

    ExpenseRequestDTO entityToRequest(Expense entity);

    Expense responseToEntity(ExpenseResponseDTO dto);

    ExpenseResponseDTO entityToResponse(Expense entity);
}
