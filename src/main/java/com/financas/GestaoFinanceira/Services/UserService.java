package com.financas.GestaoFinanceira.Services;

import com.financas.GestaoFinanceira.configuration.BaseSpecs;
import com.financas.GestaoFinanceira.domain.User;
import com.financas.GestaoFinanceira.domain.User_;
import com.financas.GestaoFinanceira.domain.dto.UserRequestDTO;
import com.financas.GestaoFinanceira.domain.dto.UserResponseDTO;
import com.financas.GestaoFinanceira.domain.mapper.UserMapper;
import com.financas.GestaoFinanceira.exceptions.BusinessException;
import com.financas.GestaoFinanceira.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class UserService implements BaseSpecs<User> {

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
	public User insert(UserRequestDTO dto) {
		existsUserInDataBase(dto.getName(), dto.getCpf(), dto.getEmail());
		User obj = mapper.requestToEntity(dto);
		return repository.save(obj);
	}

	private void existsUserInDataBase(String name, String cpf, String email){
		Specification<User> spec = Specification.where(byEquals(User_.name, name))
				.or(byEquals(User_.cpf, cpf))
				.or(byEquals(User_.email, email));
		if (repository.exists(spec)){
			throw new BusinessException("User exists!");
		}
	}
}
