package com.microservice.venta.application.ports.input;

import com.microservice.venta.domain.model.DetalleVentaModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DetalleVentaServicePort {

    Optional<DetalleVentaModel> obtenerDetalleVentaPorId(UUID id);
    List<DetalleVentaModel> listarDetalleVenta();
    DetalleVentaModel guardarDetalleVenta(DetalleVentaModel detalleVentaModel);
    DetalleVentaModel actualizarDetalleVenta(UUID id, DetalleVentaModel detalleVentaModel);
    void eliminarDetalleVenta(UUID id);

}
