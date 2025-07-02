package com.financas.gestaofinanceira.domain.dto.jasper;

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
public class ReportJasperItensDTO {

    private String expenseName;
    private Double expenseValue;
    private String categoryName;
    private Double totalValue;

}
