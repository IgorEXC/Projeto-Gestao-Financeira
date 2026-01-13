package com.financas.gestaofinanceira.services;

import com.financas.gestaofinanceira.configuration.security.CurrentUserLogged;
import com.financas.gestaofinanceira.domain.User;
import com.financas.gestaofinanceira.domain.User_;
import com.financas.gestaofinanceira.domain.dto.request.UserRequestDTO;
import com.financas.gestaofinanceira.domain.dto.response.UserResponseDTO;
import com.financas.gestaofinanceira.domain.mapper.UserMapper;
import com.financas.gestaofinanceira.domain.utils.Birthdate;
import com.financas.gestaofinanceira.exceptions.BusinessException;
import com.financas.gestaofinanceira.repositories.UserRepository;
import com.financas.gestaofinanceira.repositories.utils.BaseSpecs;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.hateoas.CollectionModel;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class UserService implements BaseSpecs<User> {

	private final UserRepository repository;
	private final UserMapper mapper;
    private final PasswordEncoder bCryptPasswordEncoder;

    //todo: regra para somente perfil de adm
	public CollectionModel<UserResponseDTO> findAllPerPage(int page, int itensPerPage){
		Page<User> pageResult = repository.findAll(PageRequest.of(page, itensPerPage));
		List<UserResponseDTO> dtoList = pageResult.stream()
				.map(mapper::entityToResponse)
				.toList();

		return CollectionModel.of(dtoList);
	}

    //todo: regra para somente perfil de adm
	public User findById(Long id) {
		return repository.findById(id)
                .orElseThrow(() -> new BusinessException("user not found"));
	}

    public UserResponseDTO getUserLogged() {
		User obj = repository.findById(CurrentUserLogged.getCurrentUserId())
                .orElseThrow(() -> new BusinessException("user not found"));
		return mapper.entityToResponse(obj);
	}

	@Transactional
	public UserResponseDTO insert(UserRequestDTO dto) {
		if(repository.exists(existsUserInDataBase(dto.getName(), dto.getCpf(), dto.getEmail()))){
				throw new BusinessException("user.exists");
		}
		User obj = mapper.requestToEntity(dto);
        Birthdate birthdate = new Birthdate(dto.getBirthdate());
        obj.setBirthdate(birthdate.getBirthdate());
        obj.setPassword(bCryptPasswordEncoder.encode(dto.getPassword()));
		repository.save(obj);
        return mapper.entityToResponse(obj);
	}

	@Transactional
	public UserResponseDTO update(UserRequestDTO dto) {
        Long userId = CurrentUserLogged.getCurrentUserId();
		User obj = findById(userId);
		if(repository.exists(existsUserInDataBase(dto.getName(), dto.getCpf(), dto.getEmail())
				.and(byNotEquals(User_.id, userId)))) {
			throw new BusinessException("user.exists");
		}
		obj = updateData(obj.getId(), dto);
		repository.save(obj);
        return mapper.entityToResponse(obj);
	}

	private User updateData(Long id, UserRequestDTO dto) {
		User obj = mapper.requestToEntity(dto);
		obj.setId(id);
        obj.setBirthdate(new Birthdate(dto.getBirthdate()).getBirthdate());
        obj.setPassword(bCryptPasswordEncoder.encode(dto.getPassword()));
		return obj;
	}

	private Specification<User> existsUserInDataBase(String name, String cpf, String email){
		return Specification.where(byEquals(User_.name, name))
				.or(byEquals(User_.cpf, cpf))
				.or(byEquals(User_.email, email));
	}

}
