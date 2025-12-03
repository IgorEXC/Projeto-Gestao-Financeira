package com.financas.gestaofinanceira.domain.dto.response;


import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FinancialPlanningResponseDTO {

    private Long id;

    private Double annualGoal;

    private Double monthlyGoal;

}
