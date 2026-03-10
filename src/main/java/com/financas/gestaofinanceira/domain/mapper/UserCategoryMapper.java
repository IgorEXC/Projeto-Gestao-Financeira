package com.financas.gestaofinanceira.domain.mapper;

import com.financas.gestaofinanceira.domain.UserCategory;
import com.financas.gestaofinanceira.domain.dto.response.CreateCategoryResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserCategoryMapper {

    CreateCategoryResponseDTO entityToCreateResponseDto(UserCategory entity);
}
