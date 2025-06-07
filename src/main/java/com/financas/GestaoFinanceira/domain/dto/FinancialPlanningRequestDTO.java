package com.financas.GestaoFinanceira.domain.dto;

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
public class FinancialPlanningRequestDTO {

    private Long id;

    private Double annualGoal;

    private Double monthlyGoal;

}
