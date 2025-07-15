package com.financas.gestaofinanceira.resources;

import com.financas.gestaofinanceira.services.ExpenseService;
import com.financas.gestaofinanceira.domain.dto.ExpenseRequestDTO;
import com.financas.gestaofinanceira.domain.dto.ExpenseResponseDTO;
import com.financas.gestaofinanceira.domain.mapper.ExpenseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/expenses")
public class ExpenseResource {

	private final ExpenseService service;
	private final ExpenseMapper mapper;

	@GetMapping(produces = {
			MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_YAML_VALUE},
			consumes = {
					MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_YAML_VALUE})
	public ResponseEntity<List<ExpenseResponseDTO>> findAll(){
		return ResponseEntity.ok().body(service.findAll());
	}

	@GetMapping(value = "/{id}",
			produces = {
					MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_YAML_VALUE},
			consumes = {
					MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_YAML_VALUE})
	public ResponseEntity<ExpenseResponseDTO> findById(@PathVariable Long id){
		return ResponseEntity.ok().body(service.findById(id));
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
	public ResponseEntity<ExpenseResponseDTO> insert(@RequestBody ExpenseRequestDTO dto){
		service.insert(dto);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

}
