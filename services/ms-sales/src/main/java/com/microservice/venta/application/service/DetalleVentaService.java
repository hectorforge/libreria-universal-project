package com.microservice.venta.application.service;

import com.microservice.venta.application.ports.input.DetalleVentaServicePort;
import com.microservice.venta.domain.model.DetalleVentaModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class DetalleVentaService implements DetalleVentaServicePort {
    @Override
    public Optional<DetalleVentaModel> obtenerDetalleVentaPorId(UUID id) {
        return Optional.empty();
    }

    @Override
    public List<DetalleVentaModel> listarDetalleVenta() {
        return List.of();
    }

    @Override
    public DetalleVentaModel guardarDetalleVenta(DetalleVentaModel detalleVentaModel) {
        return null;
    }

    @Override
    public DetalleVentaModel actualizarDetalleVenta(UUID id, DetalleVentaModel detalleVentaModel) {
        return null;
    }

    @Override
    public void eliminarDetalleVenta(UUID id) {

    }
}
