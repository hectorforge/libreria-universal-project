package com.microservice.venta.application.ports.input;

import com.microservice.venta.domain.model.FacturacionModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FacturacionServicePort {

    Optional<FacturacionModel> obtenerFacturacionPorId(UUID id);
    List<FacturacionModel> listarFacturaciones();
    FacturacionModel generarFacturacion(FacturacionModel facturacionModel);
    FacturacionModel actualizarFacturacion(FacturacionModel facturacionModel);
    void cancelarFacturacion(UUID id);
}
