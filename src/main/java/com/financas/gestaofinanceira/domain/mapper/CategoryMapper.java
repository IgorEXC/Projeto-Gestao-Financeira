package com.financas.gestaofinanceira.domain.mapper;

import com.financas.gestaofinanceira.domain.Category;
import com.financas.gestaofinanceira.domain.dto.CategoryRequestDTO;
import com.financas.gestaofinanceira.domain.dto.CategoryResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    Category requestToEntity(CategoryRequestDTO dto);

    CategoryRequestDTO entityToRequest(Category entity);

    Category responseToEntity(CategoryResponseDTO dto);

    CategoryResponseDTO entityToResponse(Category entity);
}
