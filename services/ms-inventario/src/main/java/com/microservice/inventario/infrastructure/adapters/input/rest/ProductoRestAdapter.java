package com.microservice.inventario.infrastructure.adapters.input.rest;

import com.microservice.inventario.application.ports.input.InventarioServicePort;
import com.microservice.inventario.application.ports.input.ProductoServicePort;
import com.microservice.inventario.domain.model.Producto;
import com.microservice.inventario.infrastructure.adapters.input.rest.mapper.ProductoRestMapperManual;
import com.microservice.inventario.infrastructure.adapters.input.rest.model.request.ProductoCreateRequest;
import com.microservice.inventario.infrastructure.adapters.input.rest.model.response.ProductoInventarioResponse;
import com.microservice.inventario.infrastructure.adapters.input.rest.model.response.ProductoResponse;
import com.microservice.inventario.shared.response.OperationResult;
import com.microservice.inventario.shared.response.pagination.PaginaResult;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/productos")
@Tag(name = "Producto", description = "API para la gestión de productos. Permite crear, actualizar, consultar, eliminar y listar productos, así como obtener detalles de inventario.")
@SecurityRequirement(name = "bearerAuth")
public class ProductoRestAdapter {

    private final ProductoServicePort servicePort;
    private final InventarioServicePort inventarioService;
    private final ProductoRestMapperManual restMapperManual;

    // ================= LISTAR TODOS =================
    @GetMapping("/v1/listar")
    public ResponseEntity<?> listarProductos() {
        return ResponseEntity.ok(
                OperationResult.isSuccess(
                        servicePort.listarProductos()
                                .stream()
                                .map(ProductoRestMapperManual::toResponse)
                                .toList(),
                        "Lista de productos",
                        200
                )
        );
    }
    // ================= OBTENER POR ID =================
    @GetMapping("/v1/obtener/{id}")
    public ResponseEntity<OperationResult<ProductoResponse>> obtenerProductoPorId(@PathVariable UUID id) {

        ProductoResponse response = ProductoRestMapperManual.toResponse(
                servicePort.obtenerProductoPorId(id)
        );

        return ResponseEntity.ok(
                OperationResult.isSuccess(response, "Producto obtenido", 200)
        );
    }

    ///PAGINACION

//    @GetMapping("/v1/listar-paginado")
//    public ResponseEntity<OperationResult<PaginaResult<ProductoResponse>>> listarProductosPaginados(
//            @RequestParam(defaultValue = "0") int pagina,
//            @RequestParam(defaultValue = "10") int tamanio,
//            @RequestParam(defaultValue = "id") String ordenarPor,
//            @RequestParam(defaultValue = "asc") String direccion
//    ) {
//        // Obtener lista completa de productos
//        List<Producto> productos = servicePort.listarProductos();
//
//        // Ordenar y paginar (simple ejemplo)
//        List<Producto> productosPaginados = productos.stream()
//                .skip((long) pagina * tamanio)
//                .limit(tamanio)
//                .toList();
//
//        PaginaResult<ProductoResponse> paginaResult = PaginaResult.of(
//                restMapper.toProductoResponseList(productosPaginados),
//                pagina,
//                tamanio,
//                productos.size()
//        );
//
//        return ResponseEntity.ok(
//                OperationResult.successPagination(
//                        paginaResult,
//                        "Lista de productos paginados",
//                        200
//                )
//        );
//    }

    // ================= REGISTRAR =================
    @PostMapping("/v1/registrar")
    public ResponseEntity<?> registrarProducto(@Valid @RequestBody ProductoCreateRequest request) {

        if (request.getCategoria() == null) {
            return ResponseEntity.badRequest().body(
                    OperationResult.validationError("La categoría es obligatoria")
            );
        }

        Producto producto = ProductoRestMapperManual.toModel(request);
        Producto productoGuardado = servicePort.registrarProducto(producto);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                OperationResult.isSuccess(
                        ProductoRestMapperManual.toResponse(productoGuardado),
                        "Producto creado exitosamente",
                        201
                )
        );
    }

    @PostMapping("/v1/registrar-varios")
    public ResponseEntity<?> registrarProductos(@Valid @RequestBody List<ProductoCreateRequest> request) {


        List<Producto> productos = ProductoRestMapperManual.toModelList(request);
        List<Producto> productosGuardado = servicePort.registrarVariosProductos(productos);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                OperationResult.isSuccess(
                        ProductoRestMapperManual.toResponseList(productosGuardado),
                        "Productos creados exitosamente",
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
        Producto producto = ProductoRestMapperManual.toModel(request);

        return ProductoRestMapperManual.toResponse(
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
        return ProductoRestMapperManual.toResponse(
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
        return servicePort.listarProductosPorCategoria(categoriaId)
                .stream()
                .map(ProductoRestMapperManual::toResponse)
                .toList();
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

        ProductoInventarioResponse response =
                servicePort.obtenerProductoInventarioPorId(id)
                        .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        return ResponseEntity.ok(
                OperationResult.isSuccess(
                        response,
                        "Producto con inventario obtenido",
                        200
                )
        );


    }

}