package com.financas.gestaofinanceira.configuration.security;

import com.financas.gestaofinanceira.exceptions.BusinessException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Objects;

public class CurrentUserLogged {

    public static Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(Objects.isNull(authentication)){
            throw new BusinessException("Unauthenticated user");
        }
        var user = authentication.getPrincipal();
        if(Objects.isNull(user)){
            throw new UsernameNotFoundException("Username cannot be null");
        }
        if(user instanceof JWTUserData){
            return ((JWTUserData) user).userId();
        }
        throw new UsernameNotFoundException("User not found");
    }
}
