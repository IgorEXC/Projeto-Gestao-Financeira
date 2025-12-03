package com.financas.gestaofinanceira.domain.dto.jasper;

import lombok.*;

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
