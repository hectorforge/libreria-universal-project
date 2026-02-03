package com.microservice.venta.application.service;

import com.microservice.venta.application.ports.input.VentaServicePort;
import com.microservice.venta.domain.model.VentaModel;
import com.microservice.venta.infrastructure.adapters.input.rest.model.response.VentaResponseReactivo;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class VentaService implements VentaServicePort {
    @Override
    public Optional<VentaModel> obtenerVentaPorId(UUID id) {
        return Optional.empty();
    }

    @Override
    public List<VentaModel> listarVentas() {
        return List.of();
    }

    @Override
    public VentaModel guardarVenta(VentaModel ventaModel) {
        return null;
    }

    @Override
    public VentaModel actualizarVenta(UUID id, VentaModel ventaModel) {
        return null;
    }

    @Override
    public void cancelarVenta(UUID id) {

    }

    @Override
    public List<VentaModel> listarVentasPorCliente(UUID idCliente) {
        return List.of();
    }

    @Override
    public List<VentaModel> listarVentasPorFechas(LocalDate fechaDesde, LocalDate fechaHasta) {
        return List.of();
    }

    @Override
    public Mono<VentaResponseReactivo> obtenerVentaReactivoPorId(UUID id) {
        return null;
    }
}
