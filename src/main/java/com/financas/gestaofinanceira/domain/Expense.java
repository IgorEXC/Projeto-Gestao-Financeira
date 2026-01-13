package com.financas.gestaofinanceira.domain;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Entity
@Table(name = "tb_expense") // Despesa
public class Expense extends AuditingEntity implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "expense_id")
	private Long id;

	@Column(name = "name", nullable = false, length = 50)
	private String name;

	@Column(name = "description", length = 100)
	private String description;

	@Column(name = "price", nullable = false)
	private Double price;

	@Column(name = "date")
	private LocalDate dateOfPurchase; //data da compra

	@Column(name = "necessary_expense")
	private Boolean necessaryExpense; // despesa necessária

	@ManyToMany(mappedBy = "expenses")
	private Set<ProductCategory> productCategory = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
