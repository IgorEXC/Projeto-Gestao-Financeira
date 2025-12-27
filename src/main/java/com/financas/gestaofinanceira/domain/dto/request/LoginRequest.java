package com.financas.gestaofinanceira.domain.dto.request;

import jakarta.validation.constraints.NotEmpty;

public record LoginRequest(
        @NotEmpty(message = "E-mail não pode ser vazio!") String email,
        @NotEmpty(message = "Senha não pode ser vazia!") String password
){ }
