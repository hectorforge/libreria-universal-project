package com.microservice.venta.application.ports.input;

import com.microservice.venta.domain.model.PagoModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PagoServicePort {

    Optional<PagoModel> obtenerPagoPorId(UUID id);
    List<PagoModel> listarPagos();
    PagoModel guardarPago(PagoModel pagoModel);
    PagoModel actualizarPago(UUID id, PagoModel pagoModel);
    void cancelarPago(UUID id);

}
