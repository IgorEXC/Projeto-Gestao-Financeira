package com.financas.GestaoFinanceira.domain.mapper;

import com.financas.GestaoFinanceira.domain.Category;
import com.financas.GestaoFinanceira.domain.dto.CategoryRequestDTO;
import com.financas.GestaoFinanceira.domain.dto.CategoryResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    Category requestToEntity(CategoryRequestDTO dto);

    CategoryRequestDTO entityToRequest(Category entity);

    Category responseToEntity(CategoryResponseDTO dto);

    CategoryResponseDTO entityToResponse(Category entity);
}
