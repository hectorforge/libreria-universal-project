package com.microservice.venta.application.service;

import com.microservice.venta.application.ports.input.PagoServicePort;
import com.microservice.venta.application.ports.output.PagoPersistencePort;
import com.microservice.venta.domain.model.PagoModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PagoService implements PagoServicePort {

    private final PagoPersistencePort persistence;

    @Override
    public Optional<PagoModel> obtenerPagoPorId(Integer id) {
        return persistence.obtenerPagoPorId(id);
    }

    @Override
    public List<PagoModel> listarPagos() {
        return persistence.listarPagos();
    }

    @Override
    public PagoModel guardarPago(PagoModel pagoModel) {
        return persistence.guardarPago(pagoModel);
    }

    @Override
    public PagoModel actualizarPago(Integer id, PagoModel pagoModel) {

        PagoModel pagoExistente = persistence.obtenerPagoPorId(id)
                .orElseThrow(() -> new RuntimeException("Pago no encontrado con id: " + id));

        pagoExistente.setMetodoPago(pagoModel.getMetodoPago());

        return persistence.guardarPago(pagoExistente);
    }

    @Override
    public void cancelarPago(Integer id) {
        persistence.cancelarPago(id);
    }
}
