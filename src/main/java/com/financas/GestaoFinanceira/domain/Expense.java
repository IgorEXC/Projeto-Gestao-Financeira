package com.financas.GestaoFinanceira.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tb_expense") // Despesa
public class Expense implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "value", length = 100)
	private String description;

	@Column(name = "price", nullable = false)
	private Double price;

	@Column(name = "date")
	private LocalDate date;

	@Column(name = "necessary_expense")
	private Boolean necessaryExpense; // despesa necessária
	
	@JsonIgnoreProperties("expenses")
	@ManyToMany
	@JoinTable(name = "Category_Expense",
	joinColumns = @JoinColumn(name = "expense_id"),
	inverseJoinColumns = @JoinColumn(name = "category_id"))
	private List<Category> categories = new ArrayList<>();
	
	@JsonIgnoreProperties("userExpenses")
	@OneToMany(mappedBy = "id.expense")
	private List<UserExpense> users = new ArrayList<>();
	
	@JsonIgnoreProperties("expenses")
	@ManyToOne
	@JoinColumn(name = "financial_planning_id")
	private FinancialPlanning financialPlanning;

	public Expense(Long id, String description, Double value, LocalDate date, Boolean necessaryExpense,
			FinancialPlanning financialPlanning) {
		this.id = id;
		this.description = description;
		this.price = value;
		this.date = date;
		this.necessaryExpense = necessaryExpense;
		this.financialPlanning = financialPlanning;
	}
}
