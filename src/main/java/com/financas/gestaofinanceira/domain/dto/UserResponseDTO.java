package com.financas.gestaofinanceira.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

//@JsonFilter("PasswordFilter")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"id", "cpf", "name", "email", "username", "birthday", "monthlyIncome"})
public class UserResponseDTO {

    private Long id;

    @CPF
    private String cpf;

    @NotBlank
    @Size(max = 40)
    private String name;

    @NotBlank
    @Size(max = 40)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String username;

    @NotBlank
    @Size(max = 100)
    @Email(regexp = ".+[@].+[\\.].+")
    private String email;

    @NotNull
    @Size(max = 40)
    private Double monthlyIncome;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthday;
}
