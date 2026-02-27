package com.microservice.inventario.application.ports.input;

import com.microservice.inventario.domain.model.Inventario;
import com.microservice.inventario.shared.response.TipoMovimiento;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface InventarioServicePort {

    void registrarStockInicial(UUID productoId, int stockActual, int stockMinimo);

    void manejoStock(UUID productoId, int cantidad, TipoMovimiento tipo);//si manejo es true entonces stock ++ si es false - stock

    int consultarStockProducto(UUID productoId);

    boolean validarDisponibilidadStock(UUID productoId, int cantidad);

    Optional<Inventario> obtenerInventarioPorProductoId(UUID productoId);

    List<Inventario> listarInventario();
}
