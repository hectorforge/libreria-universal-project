package com.microservice.venta.application.service;

import com.microservice.venta.application.ports.input.FacturacionServicePort;
import com.microservice.venta.domain.model.FacturacionModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FacturacionService implements FacturacionServicePort {
    @Override
    public Optional<FacturacionModel> obtenerFacturacionPorId(UUID id) {
        return Optional.empty();
    }

    @Override
    public List<FacturacionModel> listarFacturaciones() {
        return List.of();
    }

    @Override
    public FacturacionModel generarFacturacion(FacturacionModel facturacionModel) {
        return null;
    }

    @Override
    public FacturacionModel actualizarFacturacion(FacturacionModel facturacionModel) {
        return null;
    }

    @Override
    public void cancelarFacturacion(UUID id) {

    }
}
