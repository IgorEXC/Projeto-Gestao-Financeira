package com.financas.gestaofinanceira.controller;

import com.financas.gestaofinanceira.annotations.GETMultiFormat;
import com.financas.gestaofinanceira.annotations.POSTMultiFormat;
import com.financas.gestaofinanceira.domain.Report;
import com.financas.gestaofinanceira.domain.dto.request.ReportRequestDTO;
import com.financas.gestaofinanceira.domain.dto.response.ReportResponseDTO;
import com.financas.gestaofinanceira.services.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/reports")
public class ReportController {

	private final ReportService service;

	@GETMultiFormat
	public List<ReportResponseDTO> findAll() {
		return service.findAll();
	}

	@GETMultiFormat(value = "/{id}")
	public ReportResponseDTO findById(@PathVariable Long id) {
		return service.fingById(id);
	}

	@GETMultiFormat(value = "/by-cpf")
	public Report findUserCpf(@RequestParam String cpf) {
		return service.findByUserCpf(cpf);
	}

	@POSTMultiFormat
	public ResponseEntity<Void> insert(@RequestBody ReportRequestDTO dto){
		service.insert(dto);
		return ResponseEntity.noContent().build();
	}
}
