package com.financas.gestaofinanceira.controller;

import com.financas.gestaofinanceira.configuration.security.TokenConfig;
import com.financas.gestaofinanceira.domain.User;
import com.financas.gestaofinanceira.domain.dto.request.LoginRequest;
import com.financas.gestaofinanceira.domain.dto.request.UserRequestDTO;
import com.financas.gestaofinanceira.domain.dto.response.LoginResponse;
import com.financas.gestaofinanceira.domain.dto.response.UserResponseDTO;
import com.financas.gestaofinanceira.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1/auth")
public class AuthController {

    private final UserService service;
    private final AuthenticationManager authenticationManager;
    private final TokenConfig tokenConfig;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        UsernamePasswordAuthenticationToken userAndPass =
                new UsernamePasswordAuthenticationToken(request.email(), request.password());
        Authentication authentication = authenticationManager.authenticate(userAndPass);
        User user = (User) authentication.getPrincipal();
        String token = tokenConfig.generateToken(user);
        return ResponseEntity.ok(new LoginResponse(token));
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@Valid @RequestBody UserRequestDTO request){
        UserResponseDTO dto = service.insert(request);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }
}
