package com.microservice.venta.application.ports.output;

import com.microservice.venta.domain.model.FacturacionModel;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FacturacionPersistencePort {

    Optional<FacturacionModel> obtenerFacturacionPorId(UUID id);
    List<FacturacionModel> listarFacturaciones();
    FacturacionModel generarFacturacion(FacturacionModel facturacionModel);
    Optional<FacturacionModel> obtenerFacturacionPorNumero(String numFactura);
    void cancelarFacturacion(UUID id);

    List<FacturacionModel> listarFacturacionPorFechas(LocalDate fechaDesde, LocalDate fechaHasta);
}
