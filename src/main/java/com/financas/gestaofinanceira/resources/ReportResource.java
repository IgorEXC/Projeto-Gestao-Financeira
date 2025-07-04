package com.financas.gestaofinanceira.resources;

import com.financas.gestaofinanceira.Services.ReportService;
import com.financas.gestaofinanceira.domain.Report;
import com.financas.gestaofinanceira.domain.dto.ReportRequestDTO;
import com.financas.gestaofinanceira.domain.dto.ReportResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/reports")
public class ReportResource {

	private final ReportService service;

	@GetMapping
	public List<ReportResponseDTO> findAll() {
        return service.findAll();
	}

	@GetMapping(value = "/{id}")
	public ReportResponseDTO findById(@PathVariable Long id) {
        return service.fingById(id);
	}

	@GetMapping(value = "/by-cpf")
	public Report findUserCpf(@RequestParam String cpf) {
        return service.findByUserCpf(cpf);
	}

	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody ReportRequestDTO dto){
		service.insert(dto);
		return ResponseEntity.noContent().build();
	}

}
