package com.microservice.venta.application.ports.input;

import com.microservice.venta.domain.model.FacturacionModel;
import com.microservice.venta.domain.model.VentaModel;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FacturacionServicePort {

    Optional<FacturacionModel> obtenerFacturacionPorId(UUID id);
    List<FacturacionModel> listarFacturaciones();
    FacturacionModel generarFacturacion(FacturacionModel facturacionModel);
    FacturacionModel actualizarFacturacion(FacturacionModel facturacionModel);
    void cancelarFacturacion(UUID id);

    List<FacturacionModel> listarFacturacionPorFechas(LocalDate fechaDesde, LocalDate fechaHasta);
    Optional<FacturacionModel> obtenerFacturacionPorNumero(String numFactura);
}
