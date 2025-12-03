package com.financas.gestaofinanceira.domain.mapper;

import com.financas.gestaofinanceira.domain.ProductCategory;
import com.financas.gestaofinanceira.domain.dto.request.CategoryRequestDTO;
import com.financas.gestaofinanceira.domain.dto.response.CategoryResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    ProductCategory requestToEntity(CategoryRequestDTO dto);

    CategoryRequestDTO entityToRequest(ProductCategory entity);

    ProductCategory responseToEntity(CategoryResponseDTO dto);

    CategoryResponseDTO entityToResponse(ProductCategory entity);
}
