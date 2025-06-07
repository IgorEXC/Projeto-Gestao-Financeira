package com.financas.GestaoFinanceira.domain;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tb_user")
public class User implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long id;

	@Column(name = "cpf", length = 11, unique = true)
	private String cpf;

	@Column(name = "name", length = 40)
	private String name;

	@Column(name = "username", length = 40)
	private String username;

	@Column(name = "email", length = 50, unique = true, nullable = false)
	private String email;

	@Column(name = "monthly_income")
	private Double monthlyIncome; //renda mensal

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private List<FinancialPlanning> financialPlanning = new ArrayList<>();

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@OneToMany(mappedBy = "id.user", fetch = FetchType.LAZY)
	private List<UserExpense> userExpenses = new ArrayList<>();
	
//	@ElementCollection
//	@CollectionTable(name = "tb_contato")
//	private Set<String> telephoneContacts = new HashSet<>();
}
