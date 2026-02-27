package com.microservice.inventario.infrastructure.adapters.output.persistence;

import com.microservice.inventario.application.ports.output.InventarioPersistencePort;
import com.microservice.inventario.domain.model.Inventario;
import com.microservice.inventario.domain.model.Producto;
import com.microservice.inventario.infrastructure.adapters.input.rest.mapper.InventarioRestMapperManual;
import com.microservice.inventario.infrastructure.adapters.output.persistence.entity.InventarioEntity;
import com.microservice.inventario.infrastructure.adapters.output.persistence.entity.ProductoEntity;
import com.microservice.inventario.infrastructure.adapters.output.persistence.mapper.InventarioPersistenceMapper;
import com.microservice.inventario.infrastructure.adapters.output.persistence.mapper.InventarioPersistenceMapperManual;
import com.microservice.inventario.infrastructure.adapters.output.persistence.mapper.ProductoPersistenceMapperManual;
import com.microservice.inventario.infrastructure.adapters.output.persistence.repository.InventarioRepository;
import com.microservice.inventario.infrastructure.adapters.output.persistence.repository.ProductoRepository;
import com.microservice.inventario.shared.response.TipoMovimiento;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class InventarioPersistenceAdapter implements InventarioPersistencePort {

    private final InventarioRepository inventarioRepository;
    private final ProductoRepository productoRepository;

    @Override
    public Optional<Inventario> obtenerInventarioPorProductoId(UUID productoId) {
        return inventarioRepository.findByProductoIdWithProductoAndCategoria(productoId)
                .map(InventarioPersistenceMapperManual::toModel);
    }

    @Override
    public Inventario save(Inventario inventario) {
      //  return InventarioPersistenceMapperManual.toModel(
        //        inventarioRepository.save(
          //              InventarioPersistenceMapperManual.toEntity(inventario)
            //    )
        //);
   // }
        // Buscar producto real desde BD
        ProductoEntity productoEntity = productoRepository.findById(
                inventario.getProductoId().getId()
        ).orElseThrow(() -> new RuntimeException("Producto no existe"));

        // Crear inventario entity manualmente
        InventarioEntity entity = InventarioEntity.builder()
                .productoId(productoEntity)
                .stockActual(inventario.getStockActual())
                .stockMinimo(inventario.getStockMinimo())
                .build();

        return InventarioPersistenceMapperManual.toModel(
                inventarioRepository.save(entity)
        );
    }

    @Override
    public List<Inventario> findAll() {
        return inventarioRepository.findAll()
                .stream()
                .map(InventarioPersistenceMapperManual::toModel)
                .toList();
    }

    @Override
    @Transactional
    public void manejoStock(UUID productoId, int cantidad, TipoMovimiento tipo) {

        if (productoId == null) {
            throw new IllegalArgumentException("El ID del producto no puede ser null");
        }

        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a cero");
        }

        if (tipo == null) {
            throw new IllegalArgumentException("El tipo de movimiento es obligatorio");
        }

        InventarioEntity inventario = inventarioRepository.findByProductoId(productoId)
                .orElseThrow(() -> new IllegalArgumentException("El producto no existe"));

        switch (tipo) {
            case SALIDA -> {
                if (inventario.getStockActual() < cantidad) {
                    throw new RuntimeException("Stock insuficiente para realizar la salida");
                }
                inventario.setStockActual(inventario.getStockActual() - cantidad);
            }
            case ENTRADA -> {
                inventario.setStockActual(inventario.getStockActual() + cantidad);
            }
            default -> throw new IllegalArgumentException("Tipo de movimiento no soportado");
        }

        inventarioRepository.save(inventario);

    }
}
