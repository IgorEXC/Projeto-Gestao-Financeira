package com.financas.GestaoFinanceira.Services;

import com.financas.GestaoFinanceira.domain.User;
import com.financas.GestaoFinanceira.domain.dto.UserResponseDTO;
import com.financas.GestaoFinanceira.domain.mapper.UserMapper;
import com.financas.GestaoFinanceira.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class UserService {

	private final UserRepository repository;
	private final UserMapper mapper;

	public List<UserResponseDTO> findAllPerPage(int page, int itensPerPage){
		Page<User> list = repository.findAll(PageRequest.of(page, itensPerPage));
		return list.stream().map(mapper::entityToResponse).toList();
	}

	public UserResponseDTO findById(Long id) {
		User obj = repository.findById(id).orElseThrow();
		return mapper.entityToResponse(obj);
	}

	@Transactional
	public User insert(User obj) {
		return repository.save(obj);
	}
}
