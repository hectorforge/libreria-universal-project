package com.microservice.inventario.infrastructure.adapters.input.rest;

import com.microservice.inventario.application.ports.input.InventarioServicePort;
import com.microservice.inventario.application.ports.input.ProductoServicePort;
import com.microservice.inventario.domain.model.Inventario;
import com.microservice.inventario.domain.model.Producto;
import com.microservice.inventario.infrastructure.adapters.input.rest.mapper.ProductoRestMapper;
import com.microservice.inventario.infrastructure.adapters.input.rest.model.request.ProductoCreateRequest;
import com.microservice.inventario.infrastructure.adapters.input.rest.model.response.ProductoInventarioResponse;
import com.microservice.inventario.infrastructure.adapters.input.rest.model.response.ProductoResponse;
import com.microservice.inventario.shared.response.OperationResult;
import com.microservice.inventario.shared.response.pagination.PaginaResult;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("productos")
public class ProductoRestAdapter {

    private final ProductoServicePort servicePort;
    private final InventarioServicePort inventarioService;
    private final ProductoRestMapper restMapper;

    // ================= LISTAR TODOS =================
    @GetMapping("/v1/listar")
    public ResponseEntity<?> listarProductos() {
        return ResponseEntity.ok(
                OperationResult.isSuccess(
                        restMapper.toProductoResponseList(servicePort.listarProductos()),
                        "Lista de productos",
                        200
                )
        );
    }
    // ================= OBTENER POR ID =================
    @GetMapping("/v1/obtener/{id}")
    public ResponseEntity<OperationResult<ProductoResponse>> obtenerProductoPorId(@PathVariable UUID id) {

        ProductoResponse response = restMapper.toProductoResponse(
                servicePort.obtenerProductoPorId(id)
        );

        return ResponseEntity.ok(
                OperationResult.isSuccess(response, "Producto obtenido", 200)
        );
    }

    ///PAGINACION

    @GetMapping("/v1/listar-paginado")
    public ResponseEntity<OperationResult<PaginaResult<ProductoResponse>>> listarProductosPaginados(
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "10") int tamanio,
            @RequestParam(defaultValue = "id") String ordenarPor,
            @RequestParam(defaultValue = "asc") String direccion
    ) {
        // Obtener lista completa de productos
        List<Producto> productos = servicePort.listarProductos();

        // Ordenar y paginar (simple ejemplo)
        List<Producto> productosPaginados = productos.stream()
                .skip((long) pagina * tamanio)
                .limit(tamanio)
                .toList();

        PaginaResult<ProductoResponse> paginaResult = PaginaResult.of(
                restMapper.toProductoResponseList(productosPaginados),
                pagina,
                tamanio,
                productos.size()
        );

        return ResponseEntity.ok(
                OperationResult.successPagination(
                        paginaResult,
                        "Lista de productos paginados",
                        200
                )
        );
    }

    // ================= REGISTRAR =================
    @PostMapping("/v1/registrar")
    public ResponseEntity<?> registrarProducto(@Valid @RequestBody ProductoCreateRequest request) {

        if (request.getCategoria() == null) {
            return ResponseEntity.badRequest().body(
                    OperationResult.validationError("La categoría es obligatoria")
            );
        }

        Producto producto = restMapper.toProducto(request);
        Producto productoGuardado = servicePort.registrarProducto(producto);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                OperationResult.isSuccess(
                        restMapper.toProductoResponse(productoGuardado),
                        "Producto creado exitosamente",
                        201
                )
        );
    }
    // ================= ACTUALIZAR =================
    @PutMapping("/v1/api/{id}")
    public ProductoResponse actualizarProducto(
            @PathVariable UUID id,
            @Valid @RequestBody ProductoCreateRequest request
    ) {
        Producto producto = restMapper.toProducto(request);

        return restMapper.toProductoResponse(
                servicePort.actualizarProducto(id, producto)
                        .orElseThrow(() ->
                                new RuntimeException("Producto no encontrado"))
        );
    }
    // ================= CAMBIAR PRECIO =================
    @PatchMapping("/v1/api/{id}/precio")
    public ProductoResponse cambiarPrecio(
            @PathVariable UUID id,
            @RequestParam double nuevoPrecio
    ) {
        return restMapper.toProductoResponse(
                servicePort.cambiarPrecio(id, nuevoPrecio)
        );
    }
    // ================= ACTIVAR =================
    @PatchMapping("/v1/api/{id}/activar")
    public void activarProducto(@PathVariable UUID id) {
        servicePort.activarProducto(id);
    }
    // ================= LISTAR POR CATEGORIA =================
    @GetMapping("/v1/api/categoria/{categoriaId}")
    public List<ProductoResponse> listarPorCategoria(
            @PathVariable UUID categoriaId
    ) {
        return restMapper.toProductoResponseList(
                servicePort.listarProductosPorCategoria(categoriaId)
        );
    }
    // ================= ELIMINAR =================
    @DeleteMapping("/v1/eliminar/{id}")
    public ResponseEntity<?> eliminarProducto(@PathVariable UUID id) {
        servicePort.eliminarProducto(id);

        return ResponseEntity.ok(
                OperationResult.isSuccess(null, "Producto eliminado correctamente", 200)
        );
    }

    //==========Producto-inventario===================
    @GetMapping("/v1/detalle/{id}")
    public ResponseEntity<?> obtenerProductoConInventario(@PathVariable UUID id) {

        try {
            Producto producto = servicePort.obtenerProductoPorId(id);
            Inventario inventario = inventarioService.obtenerInventarioPorProductoId(id);

            return ResponseEntity.ok(
                    OperationResult.isSuccess(
                            restMapper.toProductoInventarioResponse(producto, inventario),
                            "Detalle de producto con inventario",
                            200
                    )
            );

        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(
                    OperationResult.failure("Producto no encontrado", 404, "PRODUCT_NOT_FOUND")
            );
        }
    }
}