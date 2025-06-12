package com.financas.GestaoFinanceira.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tb_financial_planning") //Planejamento financeiro
public class FinancialPlanning implements Serializable{ 

	@Serial
	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "financial_planning_id")
	private Long id;
	//meta anual nao pode ser maior que 30% do salario anual
	@Column(name = "annual_goal")
	private Double annualGoal; //meta anual de economia
	//meta mensal nao pode ser maior que 30% do salario
	@Column(name = "monthly_goal")
	private Double monthlyGoal; //meta mensal de economia
	
	@JsonIgnoreProperties("financialPlanning")
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@JsonIgnoreProperties("financialPlanning")
	@OneToMany(mappedBy = "financialPlanning")
	private List<Expense> expenses = new ArrayList<>();
}
