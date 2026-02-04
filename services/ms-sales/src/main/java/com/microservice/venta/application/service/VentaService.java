package com.microservice.venta.application.service;

import com.microservice.venta.application.ports.input.VentaServicePort;
import com.microservice.venta.application.ports.output.VentaPersistencePort;
import com.microservice.venta.domain.model.VentaModel;
import com.microservice.venta.infrastructure.adapters.input.rest.model.response.VentaReactivoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VentaService implements VentaServicePort {

    private final VentaPersistencePort ventaPersistencePort;

    @Override
    public Optional<VentaModel> obtenerVentaPorId(UUID id) {
        return ventaPersistencePort.obtenerVentaPorId(id);
    }

    @Override
    public List<VentaModel> listarVentas() {
        return ventaPersistencePort.listarVentas();
    }

    @Override
    public VentaModel guardarVenta(VentaModel ventaModel) {
        return ventaPersistencePort.guardarVenta(ventaModel);
    }

    @Override
    public VentaModel actualizarVenta(UUID id, VentaModel ventaModel) {
        return ventaPersistencePort.actualizarVenta(id, ventaModel);
    }

    @Override
    public void cancelarVenta(UUID id) {
        ventaPersistencePort.cancelarVenta(id);
    }

    @Override
    public List<VentaModel> listarVentasPorCliente(UUID idCliente) {
        return ventaPersistencePort.listarVentasPorCliente(idCliente);
    }

    @Override
    public List<VentaModel> listarVentasPorFechas(LocalDate fechaDesde, LocalDate fechaHasta) {
        return ventaPersistencePort.listarVentasPorFechas(fechaDesde, fechaHasta);
    }

    @Override
    public Mono<VentaReactivoResponse> obtenerVentaReactivoPorId(UUID id) {
        return null;
    }
}
