package com.financas.gestaofinanceira.configuration.security;

import lombok.Builder;

@Builder
public record JWTUserData(Long userId, String email){
}
