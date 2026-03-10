package com.financas.gestaofinanceira.controller;

import com.financas.gestaofinanceira.annotations.GETMultiFormat;
import com.financas.gestaofinanceira.annotations.POSTMultiFormat;
import com.financas.gestaofinanceira.annotations.PUTMultiFormat;
import com.financas.gestaofinanceira.domain.dto.request.UserRequestDTO;
import com.financas.gestaofinanceira.domain.dto.response.UserResponseDTO;
import com.financas.gestaofinanceira.domain.mapper.UserMapper;
import com.financas.gestaofinanceira.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1/users")
public class UserController {

	private final UserService service;
    private final UserMapper mapper;

	@GETMultiFormat
	public ResponseEntity<CollectionModel<UserResponseDTO>> findAllPerPage(@RequestParam int page, @RequestParam int itensPerPage){
		return ResponseEntity.ok().body(service.findAllPerPage(page, itensPerPage));
	}

	@GETMultiFormat(value = "/{id}")
	public ResponseEntity<UserResponseDTO> findById(@PathVariable Long id){
		UserResponseDTO dto = mapper.entityToResponse(service.findById(id));
		return ResponseEntity.ok().body(dto);
	}

	@POSTMultiFormat
	public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserRequestDTO dto) {
		UserResponseDTO responseDto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(responseDto.getId()).toUri();
		return ResponseEntity.created(uri).body(responseDto);
	}

	@PUTMultiFormat
	public ResponseEntity<UserResponseDTO> updateUser(@Valid @RequestBody UserRequestDTO dto){
		return ResponseEntity.ok().body(service.update(dto));
	}

    @GETMultiFormat("/user-logged")
    public ResponseEntity<UserResponseDTO> getUserLogged() {
        return ResponseEntity.ok().body(service.getUserLogged());
    }
}
