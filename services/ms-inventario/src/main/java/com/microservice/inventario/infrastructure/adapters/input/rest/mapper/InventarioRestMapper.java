package com.microservice.inventario.infrastructure.adapters.input.rest.mapper;

import com.microservice.inventario.domain.model.Inventario;
import com.microservice.inventario.infrastructure.adapters.input.rest.model.response.InventarioResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface InventarioRestMapper {

    @Mapping(source = "producto.id", target = "productoId")
    InventarioResponse toInventarioResponse(Inventario inventario);

    List<InventarioResponse> toInventarioResponseList(List<Inventario> inventarios);
}
