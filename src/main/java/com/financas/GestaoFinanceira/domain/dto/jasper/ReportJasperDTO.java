package com.financas.GestaoFinanceira.domain.dto.jasper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReportJasperDTO {

    private Long id;
    private String name;
    private String expenseName;
    private Double expenseValue;
    private String categoryName;
    private Double totalValue;
    private Double sumTotal;
    private Double sumValue;
}
