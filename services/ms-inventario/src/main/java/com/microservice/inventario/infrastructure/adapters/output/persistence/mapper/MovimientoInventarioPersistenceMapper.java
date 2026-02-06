package com.microservice.inventario.infrastructure.adapters.output.persistence.mapper;

import com.microservice.inventario.domain.model.MovimientoInventario;
import com.microservice.inventario.infrastructure.adapters.output.persistence.entity.MovimientoInventarioEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MovimientoInventarioPersistenceMapper {

    MovimientoInventarioEntity toMovimientoEntity(MovimientoInventario movimientoInventario);

    MovimientoInventario toMovimiento(MovimientoInventarioEntity entity);

    List<MovimientoInventario> toMovimientoList(List<MovimientoInventarioEntity> entityList);
}
