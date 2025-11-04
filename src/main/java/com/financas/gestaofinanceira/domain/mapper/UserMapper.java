package com.financas.gestaofinanceira.domain.mapper;

import com.financas.gestaofinanceira.domain.User;
import com.financas.gestaofinanceira.domain.dto.request.UserRequestDTO;
import com.financas.gestaofinanceira.domain.dto.response.UserResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User requestToEntity(UserRequestDTO dto);

    UserRequestDTO entityToRequest(User entity);

    User responseToEntity(UserResponseDTO dto);

    UserResponseDTO entityToResponse(User entity);
}
