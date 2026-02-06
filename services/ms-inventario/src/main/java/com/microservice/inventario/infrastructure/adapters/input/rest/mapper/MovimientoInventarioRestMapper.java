package com.microservice.inventario.infrastructure.adapters.input.rest.mapper;

import com.microservice.inventario.domain.model.MovimientoInventario;
import com.microservice.inventario.infrastructure.adapters.input.rest.model.response.MovimientoInventarioResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface MovimientoInventarioRestMapper {

    MovimientoInventarioResponse toMovimientoResponse(
            MovimientoInventario movimiento
    );

    List<MovimientoInventarioResponse> toMovimientoResponseList(
            List<MovimientoInventario> movimientos
    );
}
