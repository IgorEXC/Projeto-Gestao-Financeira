package com.financas.gestaofinanceira.services;

import com.financas.gestaofinanceira.configuration.BaseSpecs;
import com.financas.gestaofinanceira.domain.ProductCategory;
import com.financas.gestaofinanceira.domain.User;
import com.financas.gestaofinanceira.domain.User_;
import com.financas.gestaofinanceira.domain.dto.request.UserRequestDTO;
import com.financas.gestaofinanceira.domain.dto.response.ExpensesByUserResponseDTO;
import com.financas.gestaofinanceira.domain.dto.response.UserResponseDTO;
import com.financas.gestaofinanceira.domain.hateoas.UserHateoasBuilder;
import com.financas.gestaofinanceira.domain.mapper.UserMapper;
import com.financas.gestaofinanceira.exceptions.BusinessException;
import com.financas.gestaofinanceira.repositories.UserRepositoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class UserService implements BaseSpecs<User> {

	private final UserRepositoryRepository repository;
	private final UserMapper mapper;
	private final UserHateoasBuilder hateoasBuilder;
    private final ExpenseService expenseService;
    private final ProductCategoryService productCategoryService;

	public CollectionModel<UserResponseDTO> findAllPerPage(int page, int itensPerPage){
		Page<User> pageResult = repository.findAll(PageRequest.of(page, itensPerPage));
		List<UserResponseDTO> dtoList = pageResult.stream()
				.map(mapper::entityToResponse)
				.map(dto -> {
					hateoasBuilder.addHateoasLinksList(dto);
					return dto;
				})
				.toList();

		return CollectionModel.of(dtoList);
	}

	public UserResponseDTO findById(Long id) {
		User obj = repository.findById(id)
                .orElseThrow(() -> new BusinessException("user not found"));
		return mapper.entityToResponse(obj);
	}

	@Transactional
	public UserResponseDTO insert(UserRequestDTO dto) {
		if(repository.exists(existsUserInDataBase(dto.getName(), dto.getCpf(), dto.getEmail()))){
				throw new BusinessException("user.exists");
		}
		User obj = mapper.requestToEntity(dto);
		repository.save(obj);
		UserResponseDTO dtoResponse = mapper.entityToResponse(obj);
		hateoasBuilder.addHateoasLinksSingle(dtoResponse, dto);
		return dtoResponse;
	}

	@Transactional
	public UserResponseDTO update(Long id, UserRequestDTO dto) {
		User obj = mapper.responseToEntity(findById(id));
		if(repository.exists(existsUserInDataBase(dto.getName(), dto.getCpf(), dto.getEmail())
				.and(byNotEquals(User_.id, id)))){
			throw new BusinessException("user.exists");
		}
		obj = updateData(obj.getId(), dto);
		repository.save(obj);
		UserResponseDTO dtoResponse = mapper.entityToResponse(obj);
		hateoasBuilder.addHateoasLinksSingle(dtoResponse, dto);
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

    //retornar user com despesas por id do user
    public ExpensesByUserResponseDTO getExpensesByUserId(Long userId){
        UserResponseDTO user = this.findById(userId);
        var dto = new ExpensesByUserResponseDTO();

        return null;
    }

    //retornar categorias com as despesas de cada categoria por id do user
    //use streams para agrupar por categorias e ordenar tambem por categorias
    //crie outro dto que comportara o nome do usuario e dentro dele crie uma lista
    //que recebera o resultado dessa consulta. Creio que ele sim pode ser um record
    public List<ExpensesByUserResponseDTO> getCategoriesWithExpenseByUserId(Long userId){
        UserResponseDTO user = this.findById(userId);
        return null;
    }
}
