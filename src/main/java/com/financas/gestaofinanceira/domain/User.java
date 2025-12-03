package com.financas.gestaofinanceira.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Entity
@Table(name = "tb_user")
public class User extends AuditingEntity implements Serializable {

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

	@Column(name = "password", length = 40, unique = true)
	private String password;

	@Column(name = "birthdate", length = 10)
	private LocalDate birthdate;

	@Column(name = "monthly_income")
	private Double monthlyIncome; //renda mensal

// -- todo: creio que nao precisa dessa associacao nessa parte pois nao é obrigatorio o usuario ter um plano financeiro logo no seu cadastro
//	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
//	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
//	private List<FinancialPlanning> financialPlanning = new ArrayList<>();

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private Set<UserCategory> category = new HashSet<>();

}

//Criar migrations de todas as classes manualmente para treinar sql
//e para sumir com erros do flyway
