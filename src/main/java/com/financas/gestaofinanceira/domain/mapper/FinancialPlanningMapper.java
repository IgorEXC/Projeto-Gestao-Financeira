package com.financas.gestaofinanceira.domain.mapper;

import com.financas.gestaofinanceira.domain.FinancialPlanning;
import com.financas.gestaofinanceira.domain.dto.request.FinancialPlanningRequestDTO;
import com.financas.gestaofinanceira.domain.dto.response.FinancialPlanningResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FinancialPlanningMapper {

    FinancialPlanning requestToEntity(FinancialPlanningRequestDTO dto);

    FinancialPlanningRequestDTO entityToRequest(FinancialPlanning entity);

    FinancialPlanning responseToEntity(FinancialPlanningResponseDTO dto);

    FinancialPlanningResponseDTO entityToResponse(FinancialPlanning entity);
}
