package com.microservice.inventario.application.service;

import com.microservice.inventario.application.ports.input.InventarioServicePort;
import com.microservice.inventario.application.ports.output.InventarioPersistencePort;
import com.microservice.inventario.domain.exception.InventarioNotFoundException;
import com.microservice.inventario.domain.model.Inventario;
import com.microservice.inventario.domain.model.Producto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InventarioService implements InventarioServicePort {

    private final InventarioPersistencePort persistencePort;

     // Registrar stock inicial del producto

    @Override
    public void registrarStockInicial(UUID productoId, int cantidad) {


        Inventario inventario = Inventario.builder()
                .producto(
                        Producto.builder()
                                .id(productoId)
                                .build()
                )
                .stockActual(cantidad)
                .stockMinimo(0)
                .build();

        persistencePort.save(inventario);
    }

    /**
     * Manejo de stock
     * manejo = true  -> ingreso
     * manejo = false -> salida
     */
    @Override
    public void manejoStock(UUID productoId, int cantidad, boolean manejo) {

        Inventario inventario = persistencePort.findByProductoId(productoId)
                .orElseThrow(InventarioNotFoundException::new);

        //  VALIDACIÓN DE NEGOCIO (AQUÍ VA)
        if (!manejo && inventario.getStockActual() < cantidad) {
            throw new RuntimeException("Stock insuficiente para realizar la salida");
        }

        //  ACTUALIZACIÓN DE STOCK
        if (manejo) {
            inventario.setStockActual(inventario.getStockActual() + cantidad);
        } else {
            inventario.setStockActual(inventario.getStockActual() - cantidad);
        }

        persistencePort.save(inventario);
    }

     //Consultar stock actual del producto

    @Override
    public int consultarStockProducto(UUID productoId) {
        return persistencePort.findByProductoId(productoId)
                .map(Inventario::getStockActual)
                .orElse(0);
    }

     //Validar disponibilidad de stock

    @Override
    public boolean validarDisponibilidadStock(UUID productoId, int cantidad) {
        return persistencePort.findByProductoId(productoId)
                .map(inv -> inv.getStockActual() >= cantidad)
                .orElse(false);
    }

    @Override
    public Inventario obtenerInventarioPorProductoId(UUID productoId) {
        return persistencePort.findByProductoId(productoId)
                .orElseThrow(InventarioNotFoundException::new);
    }

    @Override
    public List<Inventario> listarInventario() {
        return persistencePort.findAll();
    }
}
