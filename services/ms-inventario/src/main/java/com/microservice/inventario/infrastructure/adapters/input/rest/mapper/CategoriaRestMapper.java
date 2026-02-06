package com.microservice.inventario.infrastructure.adapters.input.rest.mapper;

import com.microservice.inventario.domain.model.Categoria;
import com.microservice.inventario.infrastructure.adapters.input.rest.model.request.CategoriaCreateRequest;
import com.microservice.inventario.infrastructure.adapters.input.rest.model.response.CategoriaResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface CategoriaRestMapper {

    // Request → Dominio
    Categoria toCategoria(CategoriaCreateRequest request);

    // Dominio → Response
    CategoriaResponse toCategoriaResponse(Categoria categoria);

    List<CategoriaResponse> toCategoriaResponseList(List<Categoria> categoria);
}
