package com.financas.gestaofinanceira.domain;

import com.financas.gestaofinanceira.domain.pk.UserExpensePK;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Entity
@Table(name = "tb_user_expense")
public class UserExpense extends BaseEntity implements Serializable {

	@EqualsAndHashCode.Include
	@EmbeddedId
	private UserExpensePK id = new UserExpensePK();
	
	private Integer quantity;
}
