package com.microservice.venta.application.ports.output;

import com.microservice.venta.domain.model.DetalleVentaModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DetalleVentaPersistencePort {

    Optional<DetalleVentaModel> obtenerDetalleVentaPorId(UUID id);
    List<DetalleVentaModel> listarDetalleVenta();
    DetalleVentaModel guardarDetalleVenta(DetalleVentaModel detalleVentaModel);
    void eliminarDetalleVenta(UUID id);

}
