package com.financas.GestaoFinanceira.resources;

import com.financas.GestaoFinanceira.Services.UserService;
import com.financas.GestaoFinanceira.domain.User;
import com.financas.GestaoFinanceira.domain.dto.UserRequestDTO;
import com.financas.GestaoFinanceira.domain.dto.UserResponseDTO;
import com.financas.GestaoFinanceira.domain.mapper.UserMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/users")
public class UserResource {

	private final UserService service;
	private final UserMapper mapper;
	
	@GetMapping(value = "/all")
	public ResponseEntity<List<UserResponseDTO>> findAllPerPage(@RequestParam int page, @RequestParam int itensPerPage){
        return ResponseEntity.ok().body(service.findAllPerPage(page, itensPerPage));
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<UserResponseDTO> findById(@PathVariable Long id){
//		UserResponseDTO dto =
        return ResponseEntity.ok().body(service.findById(id));
	}

	@PostMapping(value = "/create")
	public ResponseEntity<UserRequestDTO> createUser(@Valid @RequestBody UserRequestDTO dto) {
		User user = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
}
