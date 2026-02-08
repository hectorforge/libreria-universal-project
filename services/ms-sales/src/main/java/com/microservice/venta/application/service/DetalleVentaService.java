package com.microservice.venta.application.service;

import com.microservice.venta.application.ports.input.DetalleVentaServicePort;
import com.microservice.venta.domain.model.DetalleVentaModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DetalleVentaService implements DetalleVentaServicePort {
    @Override
    public Optional<DetalleVentaModel> obtenerDetalleVentaPorId(Integer id) {
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
    public DetalleVentaModel actualizarDetalleVenta(Integer id, DetalleVentaModel detalleVentaModel) {
        return null;
    }

    @Override
    public void eliminarDetalleVenta(Integer id) {

    }
}
