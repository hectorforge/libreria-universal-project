package com.microservice.venta.application.ports.output;

import com.microservice.venta.domain.model.PagoModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PagoPersistencePort {

    Optional<PagoModel> obtenerPagoPorId(UUID id);
    List<PagoModel> listarPagos();
    PagoModel guardarPago(PagoModel pagoModel);
    void cancelarPago(UUID id);

}
