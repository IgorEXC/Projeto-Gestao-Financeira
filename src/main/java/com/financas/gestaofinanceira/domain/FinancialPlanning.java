package com.financas.gestaofinanceira.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Entity
@Table(name = "tb_financial_planning") //Planejamento financeiro
public class FinancialPlanning extends AuditingEntity implements Serializable{

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

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@OneToMany(mappedBy = "financialPlanning", fetch = FetchType.LAZY)
	private List<Expense> expenses = new ArrayList<>();
}
