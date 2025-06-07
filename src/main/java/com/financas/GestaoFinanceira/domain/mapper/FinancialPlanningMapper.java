package com.financas.GestaoFinanceira.domain.mapper;

import com.financas.GestaoFinanceira.domain.FinancialPlanning;
import com.financas.GestaoFinanceira.domain.dto.FinancialPlanningRequestDTO;
import com.financas.GestaoFinanceira.domain.dto.FinancialPlanningResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FinancialPlanningMapper {

    FinancialPlanning requestToEntity(FinancialPlanningRequestDTO dto);

    FinancialPlanningRequestDTO entityToRequest(FinancialPlanning entity);

    FinancialPlanning responseToEntity(FinancialPlanningResponseDTO dto);

    FinancialPlanningResponseDTO entityToResponse(FinancialPlanning entity);
}
