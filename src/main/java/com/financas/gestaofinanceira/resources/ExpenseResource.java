package com.financas.gestaofinanceira.resources;

import com.financas.gestaofinanceira.annotations.GETMultiFormat;
import com.financas.gestaofinanceira.annotations.POSTMultiFormat;
import com.financas.gestaofinanceira.domain.dto.response.ExpenseWithCategoryResponseDTO;
import com.financas.gestaofinanceira.services.ExpenseService;
import com.financas.gestaofinanceira.domain.dto.request.ExpenseRequestDTO;
import com.financas.gestaofinanceira.domain.dto.response.ExpenseResponseDTO;
import com.financas.gestaofinanceira.domain.mapper.ExpenseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
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

	@GETMultiFormat
	public ResponseEntity<List<ExpenseResponseDTO>> findAll(){
		return ResponseEntity.ok().body(service.findAll());
	}

	@GETMultiFormat(value = "/{id}")
	public ResponseEntity<ExpenseResponseDTO> findById(@PathVariable Long id){
		return ResponseEntity.ok().body(service.findById(id));
	}

	@POSTMultiFormat(value = "/create")
	public ResponseEntity<ExpenseResponseDTO> insert(@RequestBody ExpenseRequestDTO dto){
		service.insert(dto);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

    @GETMultiFormat(value = "/category-by-expense/{id}")
    public ResponseEntity<ExpenseWithCategoryResponseDTO> findCategoryByExpense(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.findCategoryByExpense(id));
    }

}
