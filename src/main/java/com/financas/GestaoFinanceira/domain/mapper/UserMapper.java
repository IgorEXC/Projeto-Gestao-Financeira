package com.financas.GestaoFinanceira.domain.mapper;

import com.financas.GestaoFinanceira.domain.User;
import com.financas.GestaoFinanceira.domain.dto.UserRequestDTO;
import com.financas.GestaoFinanceira.domain.dto.UserResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User requestToEntity(UserRequestDTO dto);

    UserRequestDTO entityToRequest(User entity);

    User responseToEntity(UserResponseDTO dto);

    UserResponseDTO entityToResponse(User entity);
}
