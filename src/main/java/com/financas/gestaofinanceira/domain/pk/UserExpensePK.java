package com.financas.gestaofinanceira.domain.pk;

import java.io.Serial;
import java.io.Serializable;

import com.financas.gestaofinanceira.domain.BaseEntity;
import com.financas.gestaofinanceira.domain.User;
import com.financas.gestaofinanceira.domain.Expense;

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
