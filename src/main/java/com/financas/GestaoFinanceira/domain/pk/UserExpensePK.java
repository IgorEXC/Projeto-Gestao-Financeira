package com.financas.GestaoFinanceira.domain.pk;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

import com.financas.GestaoFinanceira.domain.User;
import com.financas.GestaoFinanceira.domain.Expense;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@EqualsAndHashCode( onlyExplicitlyIncluded = true)
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class UserExpensePK implements Serializable { //Despesas da categoria

	@Serial
	private static final long serialVersionUID = 1L; 

	@EqualsAndHashCode.Include
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@EqualsAndHashCode.Include
	@ManyToOne
	@JoinColumn(name = "expense_id")
	private Expense expense;

}
