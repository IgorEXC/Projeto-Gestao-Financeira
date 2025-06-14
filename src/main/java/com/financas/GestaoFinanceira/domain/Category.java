package com.financas.GestaoFinanceira.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tb_category")
public class Category implements Serializable{
	@Serial
	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "category_name", length = 40, unique = true)
	private String name;

	@Column(name = "predicted_category_limit")
	private Double predictedCategoryLimit; //limite previsto da categoria
	
//	@JsonIgnoreProperties("categories")
//	@ManyToMany(mappedBy = "categories")
//	List<Expense> expenses = new ArrayList<>();
}
