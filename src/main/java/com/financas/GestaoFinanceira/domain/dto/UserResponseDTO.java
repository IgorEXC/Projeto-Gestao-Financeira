package com.financas.GestaoFinanceira.domain.dto;

import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {

    private Long id;

    @CPF
    @Size(min = 11, max = 11)
    private String cpf;

    @Size(max = 40)
    private String name;

    @Size(max = 40)
    private String username;

    @Size(max = 40)
    private String email;

    @Size(max = 40)
    private Double monthlyIncome;
}
