package com.microservice.inventario.application.service;

import com.microservice.inventario.application.ports.input.CategoriaServicePort;
import com.microservice.inventario.application.ports.input.ProductoServicePort;
import com.microservice.inventario.application.ports.output.ProductoPersistencePort;
import com.microservice.inventario.domain.exception.ProductoNotFoundException;
import com.microservice.inventario.domain.model.Categoria;
import com.microservice.inventario.domain.model.Producto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductoService implements ProductoServicePort {

    private final ProductoPersistencePort productoPersistencePort;
    private final CategoriaServicePort categoriaService;

     // Registrar producto
     @Override
     public Producto registrarProducto(Producto producto) {
         // Buscar la categoría real por id
         Categoria categoria = categoriaService.obtenerCategoriaPorId(producto.getCategoria().getId());
         producto.setCategoria(categoria);

         producto.setEstado(true); // por defecto activo
         return productoPersistencePort.save(producto);
     }

     // Actualizar datos generales del producto
    @Override
    public Optional<Producto> actualizarProducto(UUID id, Producto producto) {
        return productoPersistencePort.findById(id)
                .map(saveProducto -> {
                    saveProducto.setCodigo(producto.getCodigo());
                    saveProducto.setNombre(producto.getNombre());
                    saveProducto.setDescripcion(producto.getDescripcion());
                    saveProducto.setCategoria(producto.getCategoria());
                    saveProducto.setPrecioActual(producto.getPrecioActual());
                    return productoPersistencePort.save(saveProducto);
                });
    }

     // Activar producto (estado = true)
    @Override
    public void activarProducto(UUID id) {
        Producto producto = productoPersistencePort.findById(id)
                .orElseThrow(ProductoNotFoundException::new);

        producto.setEstado(true);
        productoPersistencePort.save(producto);
    }

     // Cambiar precio del producto
    @Override
    public Producto cambiarPrecio(UUID id, double nuevoPrecio) {
        Producto producto = productoPersistencePort.findById(id)
                .orElseThrow(ProductoNotFoundException::new);

        producto.setPrecioActual(nuevoPrecio);
        return productoPersistencePort.save(producto);
    }

      // Obtener producto por ID
    @Override
    public Producto obtenerProductoPorId(UUID id) {
        return productoPersistencePort.findById(id)
                .orElseThrow(ProductoNotFoundException::new);
    }

     // Listar todos los productos
    @Override
    public List<Producto> listarProductos() {
        return productoPersistencePort.findAll();
    }

     // Listar productos por categoría
    @Override
    public List<Producto> listarProductosPorCategoria(UUID categoriaId) {
        return productoPersistencePort.findByCategoriaId(categoriaId);
    }

    //Eliminar Producto por ID
    @Override
    public void eliminarProducto(UUID id) {
        if (productoPersistencePort.findById(id).isEmpty()) {
            throw new ProductoNotFoundException();
        }
        productoPersistencePort.deleteById(id);
    }

}
