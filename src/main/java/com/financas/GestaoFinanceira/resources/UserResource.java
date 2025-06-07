package com.financas.GestaoFinanceira.resources;

import java.util.List;

import com.financas.GestaoFinanceira.domain.dto.UserResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import com.financas.GestaoFinanceira.Services.UserService;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/users")
public class UserResource {

	private final UserService service;
	
	@GetMapping(value = "/all")
	public List<UserResponseDTO> findAllPerPage(@RequestParam int page, @RequestParam int itensPerPage){
        return service.findAllPerPage(page, itensPerPage);
	}
	
	@GetMapping(value = "/{id}")
	public UserResponseDTO findById(@PathVariable Long id){
        return service.findById(id);
	}
	
	
}
