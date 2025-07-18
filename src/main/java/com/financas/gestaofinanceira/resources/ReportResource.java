package com.financas.gestaofinanceira.resources;

import com.financas.gestaofinanceira.services.ReportService;
import com.financas.gestaofinanceira.domain.Report;
import com.financas.gestaofinanceira.domain.dto.ReportRequestDTO;
import com.financas.gestaofinanceira.domain.dto.ReportResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/reports")
public class ReportResource {

	private final ReportService service;

	@GetMapping(produces = {
			MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_YAML_VALUE})
	public List<ReportResponseDTO> findAll() {
		return service.findAll();
	}

	@GetMapping(value = "/{id}",
			produces = {
					MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_YAML_VALUE})
	public ReportResponseDTO findById(@PathVariable Long id) {
		return service.fingById(id);
	}

	@GetMapping(value = "/by-cpf",
			produces = {
					MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_YAML_VALUE})
	public Report findUserCpf(@RequestParam String cpf) {
		return service.findByUserCpf(cpf);
	}

	@PostMapping(produces = {
			MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_YAML_VALUE},
			consumes = {
					MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_YAML_VALUE})
	public ResponseEntity<Void> insert(@RequestBody ReportRequestDTO dto){
		service.insert(dto);
		return ResponseEntity.noContent().build();
	}

}
