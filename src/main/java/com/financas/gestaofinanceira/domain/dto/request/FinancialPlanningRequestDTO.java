package com.financas.gestaofinanceira.domain.dto.request;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FinancialPlanningRequestDTO {

    private Long id;

    private Double annualGoal;

    private Double monthlyGoal;

}
