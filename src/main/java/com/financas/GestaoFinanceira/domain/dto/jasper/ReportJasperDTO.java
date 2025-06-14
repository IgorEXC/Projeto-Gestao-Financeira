package com.financas.GestaoFinanceira.domain.dto.jasper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReportJasperDTO {

    private Long id;
    private String name;
    private Double sumTotal;
    private Double sumValue;

    List<ReportJasperItensDTO> listExpense;
}
