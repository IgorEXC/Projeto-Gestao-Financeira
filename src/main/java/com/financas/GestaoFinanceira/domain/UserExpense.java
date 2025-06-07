package com.financas.GestaoFinanceira.domain;

import com.financas.GestaoFinanceira.domain.pk.UserExpensePK;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tb_user_expense")
public class UserExpense {

	@EqualsAndHashCode.Include
	@EmbeddedId
	private UserExpensePK id = new UserExpensePK();
	
	private Integer quantity;
}
