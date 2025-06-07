package com.financas.GestaoFinanceira.domain.mapper;

import com.financas.GestaoFinanceira.domain.Report;
import com.financas.GestaoFinanceira.domain.dto.ReportResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReportMapper {

    Report responseToEntity(ReportResponseDTO dto);

    ReportResponseDTO entityToResponse(Report entity);
}
