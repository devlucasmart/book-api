package com.devlucasmart.book.mappers;

import com.devlucasmart.book.dto.categoria.CategoriaRequest;
import com.devlucasmart.book.dto.categoria.CategoriaResponse;
import com.devlucasmart.book.model.CategoriaModel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface CategoriaMapper {
    CategoriaModel toDomain(CategoriaRequest request);
    CategoriaRequest toRequest(CategoriaModel categoria);
    CategoriaResponse toResponse(CategoriaModel categoria);
    List<CategoriaResponse> toListResponse(List<CategoriaModel> categoria);
}
