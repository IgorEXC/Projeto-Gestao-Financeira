package com.financas.GestaoFinanceira.resources;

import com.financas.GestaoFinanceira.Services.ReportService;
import com.financas.GestaoFinanceira.domain.Report;
import com.financas.GestaoFinanceira.domain.dto.ReportDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/reports")
public class ReportResource {

	private final ReportService service;

	@GetMapping
	public List<ReportDTO> findAll() {
        return service.findAll();
	}

	@GetMapping(value = "/{id}")
	public ReportDTO findById(@PathVariable Long id) {
        return service.fingById(id);
	}

	@GetMapping(value = "/by-cpf")
	public Report findUserCpf(@RequestParam String cpf) {
        return service.findByUserCpf(cpf);
	}
}
