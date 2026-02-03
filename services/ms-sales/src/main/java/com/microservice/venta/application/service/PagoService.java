package com.microservice.venta.application.service;

import com.microservice.venta.application.ports.input.PagoServicePort;
import com.microservice.venta.domain.model.PagoModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class PagoService implements PagoServicePort {
    @Override
    public Optional<PagoModel> obtenerPagoPorId(UUID id) {
        return Optional.empty();
    }

    @Override
    public List<PagoModel> listarPagos() {
        return List.of();
    }

    @Override
    public PagoModel guardarPago(PagoModel pagoModel) {
        return null;
    }

    @Override
    public PagoModel actualizarPago(UUID id, PagoModel pagoModel) {
        return null;
    }

    @Override
    public void cancelarPago(UUID id) {

    }
}
