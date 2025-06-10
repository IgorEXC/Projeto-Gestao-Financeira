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

	public User findById(Long id) {
		return repository.findById(id).orElseThrow();
	}

	@Transactional
	public User insert(UserRequestDTO dto) {
		if(repository.exists(existsUserInDataBase(dto.getName(), dto.getCpf(), dto.getEmail()))){
				throw new BusinessException("User exists!");
		}
		User obj = mapper.requestToEntity(dto);
		return repository.save(obj);
	}

	private Specification<User> existsUserInDataBase(String name, String cpf, String email){
		return Specification.where(byEquals(User_.name, name))
				.or(byEquals(User_.cpf, cpf))
				.or(byEquals(User_.email, email));
	}

	@Transactional
	public void update(Long id, UserRequestDTO dto) {
		User obj = findById(id);
		if(repository.exists(existsUserInDataBase(dto.getName(), dto.getCpf(), dto.getEmail())
				.and(byNotEquals(User_.id, id)))){
			throw new BusinessException("User exists!");
		}
		obj = updateData(obj.getId(), dto);
		repository.save(obj);
	}

	private User updateData(Long id, UserRequestDTO dto) {
		User obj = mapper.requestToEntity(dto);
		obj.setId(id);
		return obj;
	}
}
