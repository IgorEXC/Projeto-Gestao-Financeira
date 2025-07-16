package com.financas.gestaofinanceira.services;

import com.financas.gestaofinanceira.configuration.BaseSpecs;
import com.financas.gestaofinanceira.domain.User;
import com.financas.gestaofinanceira.domain.User_;
import com.financas.gestaofinanceira.domain.dto.UserRequestDTO;
import com.financas.gestaofinanceira.domain.dto.UserResponseDTO;
import com.financas.gestaofinanceira.domain.hateoas.UserHateoasBuilder;
import com.financas.gestaofinanceira.domain.mapper.UserMapper;
import com.financas.gestaofinanceira.exceptions.BusinessException;
import com.financas.gestaofinanceira.repositories.UserRepository;
import com.financas.gestaofinanceira.resources.UserResource;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class UserService implements BaseSpecs<User> {

	private final UserRepository repository;
	private final UserMapper mapper;
	private final UserHateoasBuilder hateoasBuilder;

	public List<UserResponseDTO> findAllPerPage(int page, int itensPerPage){
		Page<User> list = repository.findAll(PageRequest.of(page, itensPerPage));
		return list.stream().map(user -> {
			UserResponseDTO dto = mapper.entityToResponse(user);
			addHateoasLinksList(dto);
			return dto;
		}).toList();
	}

	public UserResponseDTO findById(Long id) {
		User obj = repository.findById(id).orElseThrow();
		return mapper.entityToResponse(obj);
	}

	@Transactional
	public UserResponseDTO insert(UserRequestDTO dto) {
		if(repository.exists(existsUserInDataBase(dto.getName(), dto.getCpf(), dto.getEmail()))){
				throw new BusinessException("User exists!");
		}
		User obj = mapper.requestToEntity(dto);
		repository.save(obj);
		UserResponseDTO dtoResponse = mapper.entityToResponse(obj);
		addHateoasLinksSingle(dtoResponse, dto);
		return dtoResponse;
	}

	@Transactional
	public UserResponseDTO update(Long id, UserRequestDTO dto) {
		User obj = mapper.responseToEntity(findById(id));
		if(repository.exists(existsUserInDataBase(dto.getName(), dto.getCpf(), dto.getEmail())
				.and(byNotEquals(User_.id, id)))){
			throw new BusinessException("User exists!");
		}
		obj = updateData(obj.getId(), dto);
		repository.save(obj);
		UserResponseDTO dtoResponse = mapper.entityToResponse(obj);
		addHateoasLinksSingle(dtoResponse, dto);
		return dtoResponse;
	}

	private User updateData(Long id, UserRequestDTO dto) {
		User obj = mapper.requestToEntity(dto);
		obj.setId(id);
		return obj;
	}

	private Specification<User> existsUserInDataBase(String name, String cpf, String email){
		return Specification.where(byEquals(User_.name, name))
				.or(byEquals(User_.cpf, cpf))
				.or(byEquals(User_.email, email));
	}

	private void addHateoasLinksSingle(UserResponseDTO dtoResponse, UserRequestDTO dtoRequest) {
		dtoResponse.add(linkTo(methodOn(UserResource.class).findById(dtoResponse.getId()))
				.withSelfRel().withType("GET"));
		dtoResponse.add(linkTo(methodOn(UserResource.class).createUser(dtoRequest)).withRel("Create")
				.withType("POST"));
		dtoResponse.add(linkTo(methodOn(UserResource.class).updateUser(dtoResponse.getId(), dtoRequest))
				.withRel("Update").withType("PUT"));
	}

	private void addHateoasLinksList(UserResponseDTO dtoResponse) {
		dtoResponse.add(linkTo(methodOn(UserResource.class).findById(dtoResponse.getId()))
				.withSelfRel().withType("GET"));
		dtoResponse.add(linkTo(methodOn(UserResource.class).updateUser(dtoResponse.getId(), null))
				.withRel("Update").withType("PUT"));
	}
}
