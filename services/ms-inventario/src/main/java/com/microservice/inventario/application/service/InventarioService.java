package com.microservice.inventario.application.service;

import com.microservice.inventario.application.ports.input.InventarioServicePort;
import com.microservice.inventario.application.ports.output.InventarioPersistencePort;
import com.microservice.inventario.domain.exception.InventarioNotFoundException;
import com.microservice.inventario.domain.model.Inventario;
import com.microservice.inventario.domain.model.Producto;
import com.microservice.inventario.shared.response.TipoMovimiento;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InventarioService implements InventarioServicePort {

    private final InventarioPersistencePort persistencePort;

     // Registrar stock inicial del producto

    @Override
    public void registrarStockInicial(UUID productoId, int stockActual, int stockMinimo) {

        if (persistencePort.obtenerInventarioPorProductoId(productoId).isPresent()) {
            throw new RuntimeException("El inventario ya existe para este producto");
        }

        Inventario inventario = Inventario.builder()
                .productoId(
                        Producto.builder()
                                .id(productoId)
                                .build()
                )
                .stockActual(stockActual)
                .stockMinimo(stockMinimo) //ahora sí usa el valor real
                .build();

        persistencePort.save(inventario);
    }

    /**
     * Manejo de stock
     * manejo = true  -> ingreso
     * manejo = false -> salida
     */
    @Override
    public void manejoStock(UUID productoId, int cantidad, TipoMovimiento tipo) {
        persistencePort.manejoStock(productoId, cantidad, tipo);
    }

     //Consultar stock actual del producto

    @Override
    public int consultarStockProducto(UUID productoId) {
        return persistencePort.obtenerInventarioPorProductoId(productoId)
                .map(Inventario::getStockActual)
                .orElse(0);
    }

     //Validar disponibilidad de stock

    @Override
    public boolean validarDisponibilidadStock(UUID productoId, int cantidad) {
        return persistencePort.obtenerInventarioPorProductoId(productoId)
                .map(inv -> inv.getStockActual() >= cantidad)
                .orElse(false);
    }

    @Override
    public Optional<Inventario> obtenerInventarioPorProductoId(UUID productoId) {
        return persistencePort.obtenerInventarioPorProductoId(productoId);
    }

    @Override
    public List<Inventario> listarInventario() {
        return persistencePort.findAll();
    }
}
