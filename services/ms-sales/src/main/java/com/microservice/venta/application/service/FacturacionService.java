package com.microservice.venta.application.service;

import com.microservice.venta.application.ports.input.FacturacionServicePort;
import com.microservice.venta.application.ports.output.FacturacionPersistencePort;
import com.microservice.venta.domain.model.FacturacionModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FacturacionService implements FacturacionServicePort {

    private final FacturacionPersistencePort persistencePort;

    @Override
    public Optional<FacturacionModel> obtenerFacturacionPorId(UUID id) {
        return persistencePort.obtenerFacturacionPorId(id);
    }

    @Override
    public Optional<FacturacionModel> obtenerFacturacionPorNumero(String numFactura) {
        return persistencePort.obtenerFacturacionPorNumero(numFactura);
    }

    @Override
    public List<FacturacionModel> listarFacturaciones() {
        return persistencePort.listarFacturaciones();
    }

    @Override
    public FacturacionModel generarFacturacion(FacturacionModel facturacionModel) {
        facturacionModel.validar();
        return persistencePort.generarFacturacion(facturacionModel);
    }

    @Override
    public FacturacionModel actualizarFacturacion(FacturacionModel facturacionModel) {
        return null;
    }

    @Override
    public void cancelarFacturacion(UUID id) {
        persistencePort.cancelarFacturacion(id);
    }

    @Override
    public List<FacturacionModel> listarFacturacionPorFechas(LocalDate fechaDesde, LocalDate fechaHasta) {
        return persistencePort.listarFacturacionPorFechas(fechaDesde, fechaHasta);
    }
}
