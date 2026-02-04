package com.microservice.venta.application.service;

import com.microservice.venta.application.ports.input.PagoServicePort;
import com.microservice.venta.domain.model.PagoModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PagoService implements PagoServicePort {
    @Override
    public Optional<PagoModel> obtenerPagoPorId(Integer id) {
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
    public PagoModel actualizarPago(Integer id, PagoModel pagoModel) {
        return null;
    }

    @Override
    public void cancelarPago(Integer id) {

    }
}
