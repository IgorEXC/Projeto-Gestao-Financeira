package com.financas.gestaofinanceira.domain.mapper;

import com.financas.gestaofinanceira.domain.Report;
import com.financas.gestaofinanceira.domain.dto.ReportRequestDTO;
import com.financas.gestaofinanceira.domain.dto.ReportResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReportMapper {

    Report responseToEntity(ReportResponseDTO dto);

    ReportResponseDTO entityToResponse(Report entity);

    ReportRequestDTO entityToRequest(Report entity);

    Report requestToEntity(ReportRequestDTO dto);
}
