package com.microservice.venta.infrastructure.adapters.output.persistence.implementacion;

import com.microservice.venta.application.ports.output.DetalleVentaPersistencePort;
import com.microservice.venta.domain.model.DetalleVentaModel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class DetalleVentaPersistenceAdapter implements DetalleVentaPersistencePort {
    @Override
    public Optional<DetalleVentaModel> obtenerDetalleVentaPorId(Integer id) {
        return Optional.empty();
    }

    @Override
    public List<DetalleVentaModel> listarDetalleVenta() {
        return List.of();
    }

    @Override
    public DetalleVentaModel guardarDetalleVenta(DetalleVentaModel detalleVentaModel) {
        return null;
    }

    @Override
    public void eliminarDetalleVenta(Integer id) {

    }
}
