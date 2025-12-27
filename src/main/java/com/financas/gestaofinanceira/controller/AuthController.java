package com.financas.gestaofinanceira.controller;

import com.financas.gestaofinanceira.configuration.security.TokenConfig;
import com.financas.gestaofinanceira.domain.User;
import com.financas.gestaofinanceira.domain.dto.request.LoginRequest;
import com.financas.gestaofinanceira.domain.dto.request.UserRequestDTO;
import com.financas.gestaofinanceira.domain.dto.response.LoginResponse;
import com.financas.gestaofinanceira.domain.dto.response.RegisterUserResponse;
import com.financas.gestaofinanceira.repositories.UserRepository;
import com.financas.gestaofinanceira.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final UserService service;
    private final AuthenticationManager authenticationManager;
    //private final PasswordEncoder bCryptPasswordEncoder;
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
    public ResponseEntity<RegisterUserResponse> register(@Valid @RequestBody UserRequestDTO request){
//        User newUser = User.builder()
//                .cpf(request.getCpf())
//                .name(request.getName())
//                .username(request.getUsername())
//                .email(request.getEmail())
//                .monthlyIncome(request.getMonthlyIncome())
//                .password(bCryptPasswordEncoder.encode(request.getPassword()))
//                .birthdate(request.getBirthdate())
//                .build();
        service.insert(request); //TODO mudar para receber a responseDTO correta e passar no ResponseEntity
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new RegisterUserResponse(request.getName(), request.getEmail()));
    }
}
