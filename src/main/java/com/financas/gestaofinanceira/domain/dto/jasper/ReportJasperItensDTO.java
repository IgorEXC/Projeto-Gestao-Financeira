package com.financas.gestaofinanceira.domain.dto.jasper;

import lombok.*;

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
