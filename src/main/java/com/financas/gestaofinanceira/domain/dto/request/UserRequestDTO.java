package com.financas.gestaofinanceira.domain.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDTO extends RepresentationModel<UserRequestDTO> {

    @NotBlank
    @CPF
    private String cpf;

    @NotBlank
    @Size(max = 40)
    private String name;

    @NotBlank
    @Size(max = 60)
    private String username;

    @NotBlank
    @Size(max = 100)
    @Email(regexp = ".+[@].+[\\.].+")
    private String email;

    @NotNull
    private Double monthlyIncome;

    @Min(8)
    @Max(40)
    @Size(max = 40)
    private String password;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthdate;

    /* @Min(0)
    @NotBlank
    @Size(min = 10, max = 11)
    @Max(99999999999L)
    private String phoneNumber;*/
}
