package com.financas.gestaofinanceira.resources;

import com.financas.gestaofinanceira.services.UserService;
import com.financas.gestaofinanceira.domain.User;
import com.financas.gestaofinanceira.domain.dto.UserRequestDTO;
import com.financas.gestaofinanceira.domain.dto.UserResponseDTO;
import com.financas.gestaofinanceira.domain.mapper.UserMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/users")
public class UserResource {

	private final UserService service;
	private final UserMapper mapper;

	@GetMapping(value = "/all",
			produces = {
					MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_YAML_VALUE})
	public ResponseEntity<List<UserResponseDTO>> findAllPerPage(@RequestParam int page, @RequestParam int itensPerPage){
		return ResponseEntity.ok().body(service.findAllPerPage(page, itensPerPage));
	}

	@GetMapping(value = "/{id}",
			produces = {
					MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_YAML_VALUE})
	public ResponseEntity<UserResponseDTO> findById(@PathVariable Long id){
		UserResponseDTO dto = mapper.entityToResponse(service.findById(id));
		return ResponseEntity.ok().body(dto);
	}

	@PostMapping(value = "/create",
			produces = {
					MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_YAML_VALUE},
			consumes = {
					MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_YAML_VALUE})
	public ResponseEntity<UserRequestDTO> createUser(@Valid @RequestBody UserRequestDTO dto) {
		User user = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping(value = "/update/{id}",
			produces = {
					MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_YAML_VALUE},
			consumes = {
					MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_YAML_VALUE})
	public ResponseEntity<UserRequestDTO> updateUser(@PathVariable Long id, @Valid @RequestBody UserRequestDTO dto){
		service.update(id, dto);
		return ResponseEntity.ok().build();
	}
}
