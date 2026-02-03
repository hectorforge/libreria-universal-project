package com.microservice.venta.application.ports.output;

import com.microservice.venta.domain.model.FacturacionModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FacturacionPersistencePort {

    Optional<FacturacionModel> obtenerFacturacionPorId(UUID id);
    List<FacturacionModel> listarFacturaciones();
    FacturacionModel generarFacturacion(FacturacionModel facturacionModel);
    void cancelarFacturacion(UUID id);
}
