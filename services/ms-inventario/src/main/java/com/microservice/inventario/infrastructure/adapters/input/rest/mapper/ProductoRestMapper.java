package com.microservice.inventario.infrastructure.adapters.input.rest.mapper;

import com.microservice.inventario.domain.model.Inventario;
import com.microservice.inventario.domain.model.Producto;
import com.microservice.inventario.infrastructure.adapters.input.rest.model.request.ProductoCreateRequest;
import com.microservice.inventario.infrastructure.adapters.input.rest.model.response.CategoriaResponse;
import com.microservice.inventario.infrastructure.adapters.input.rest.model.response.ProductoInventarioResponse;
import com.microservice.inventario.infrastructure.adapters.input.rest.model.response.ProductoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
        componentModel = "spring",//genera un bean de Spring
        unmappedTargetPolicy = ReportingPolicy.IGNORE//aca mapeas los campos que no se usan especificamente
)
public interface ProductoRestMapper {

    // ===== Request → Dominio =====
    @Mapping(target = "categoria", source = "categoria")
    Producto toProducto(ProductoCreateRequest request);

    // ===== Dominio → Response =====
    ProductoResponse toProductoResponse(Producto producto);
    // Listas
    List<ProductoResponse> toProductoResponseList(List<Producto> productos);

    // ===== Producto + Inventario =====
    default ProductoInventarioResponse toProductoInventarioResponse(
            Producto producto,
            Inventario inventario
    ) {
        return new ProductoInventarioResponse(
                producto.getId(),
                producto.getCodigo(),
                producto.getNombre(),
                producto.getDescripcion(),
                producto.getPrecioActual(),
                producto.isEstado(),
                producto.getUrlImagen(),
                producto.getCategoria() != null ? producto.getCategoria().getId() : null,
                producto.getCategoria() != null ? producto.getCategoria().getNombre() : null,
                inventario.getStockActual(),
                inventario.getStockMinimo()
        );
    }
}