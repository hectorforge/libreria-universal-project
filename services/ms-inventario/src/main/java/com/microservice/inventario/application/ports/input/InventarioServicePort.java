package com.microservice.inventario.application.ports.input;

import com.microservice.inventario.domain.model.Inventario;

import java.util.List;
import java.util.UUID;

public interface InventarioServicePort {

    void registrarStockInicial(UUID productoId, int cantidad);

    void manejoStock(UUID productoId, int cantidad, boolean manejo);//si manejo es true entonces stock ++ si es false - stock

    int consultarStockProducto(UUID productoId);

    boolean validarDisponibilidadStock(UUID productoId, int cantidad);

    Inventario obtenerInventarioPorProductoId(UUID productoId);

    List<Inventario> listarInventario();
}
