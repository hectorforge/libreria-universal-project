package com.microservice.venta.infrastructure.adapters.output.persistence.implementacion;

import com.microservice.venta.application.ports.output.PagoPersistencePort;
import com.microservice.venta.domain.model.PagoModel;
import com.microservice.venta.infrastructure.adapters.output.persistence.entity.PagoEntity;
import com.microservice.venta.infrastructure.adapters.output.persistence.mapper.PagoPersistenceMapper;
import com.microservice.venta.infrastructure.adapters.output.persistence.repository.PagoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class PagoPersistenceAdapter implements PagoPersistencePort {

    private final PagoRepository repository;

    @Override
    public Optional<PagoModel> obtenerPagoPorId(Integer id) {
        return repository.obtenerPagoActivoPorId(id)
                .map(PagoPersistenceMapper::toResponse);
    }

    @Override
    public List<PagoModel> listarPagos() {
        return repository.listarPagosActivos()
                .stream()
                .map(PagoPersistenceMapper::toResponse)
                .toList();
    }

    @Override
    public PagoModel guardarPago(PagoModel pagoModel) {
        PagoEntity entity;

        if (pagoModel.getId() != null) {
            // UPDATE
            entity = repository.findById(pagoModel.getId())
                    .orElseThrow(() -> new RuntimeException("Pago no encontrado"));

            entity.setMetodoPago(pagoModel.getMetodoPago());

        } else {
            // CREATE
            entity = PagoPersistenceMapper.toEntity(pagoModel);
        }

        return PagoPersistenceMapper.toResponse(repository.save(entity));
    }

    @Override
    public void cancelarPago(Integer id) {
        PagoEntity pago = repository.obtenerPagoActivoPorId(id)
                .orElseThrow(() -> new RuntimeException("Pago no encontrado con id: " + id));

        pago.setActivo(false);
        pago.setEstado("CANCELADO");
        repository.save(pago);
    }
}
