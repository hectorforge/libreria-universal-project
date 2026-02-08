package com.microservice.venta.application.ports.input;

import com.microservice.venta.domain.model.PagoModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PagoServicePort {

    Optional<PagoModel> obtenerPagoPorId(Integer id);
    List<PagoModel> listarPagos();
    PagoModel guardarPago(PagoModel pagoModel);
    PagoModel actualizarPago(Integer id, PagoModel pagoModel);
    void cancelarPago(Integer id);

}
