package com.microservice.venta.application.ports.input;

import com.microservice.venta.domain.model.VentaModel;
import com.microservice.venta.infrastructure.adapters.input.rest.model.response.VentaResponseReactivo;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface VentaServicePort {

    //Métodos principales del servicio de venta
    Optional<VentaModel> obtenerVentaPorId(UUID id);
    List<VentaModel> listarVentas();
    VentaModel guardarVenta(VentaModel ventaModel);
    VentaModel actualizarVenta(UUID id, VentaModel ventaModel);
    void cancelarVenta(UUID id);

    //Métodos adicionales relacionados con la lógica de negocio de ventas
    List<VentaModel> listarVentasPorCliente(UUID idCliente);
    List<VentaModel> listarVentasPorFechas(LocalDate fechaDesde, LocalDate fechaHasta);

    // Otros métodos específicos pueden ser añadidos aquí según los requisitos del negocio - reactivo
    Mono<VentaResponseReactivo> obtenerVentaReactivoPorId(UUID id);
}
