package com.microservice.venta.infrastructure.adapters.input.rest.mapper;

import com.microservice.venta.domain.model.DetalleVentaModel;
import com.microservice.venta.infrastructure.adapters.input.rest.model.request.DetalleVentaRequest;
import com.microservice.venta.infrastructure.adapters.input.rest.model.response.DetalleVentaResponse;
import com.microservice.venta.infrastructure.adapters.input.rest.model.response.feign.DetalleVentaCompletaFeign;
import com.microservice.venta.infrastructure.adapters.output.client.response.DataResponse;
import com.microservice.venta.infrastructure.adapters.output.client.response.ProductoFeignClient;
import com.microservice.venta.infrastructure.adapters.output.client.response.ProductoResponse;
import lombok.RequiredArgsConstructor;
import lombok.experimental.UtilityClass;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DetalleVentaRestMapper {

    private final ProductoFeignClient productoFeignClient;

    public static DetalleVentaResponse toResponse(DetalleVentaModel model) {
        return DetalleVentaResponse.builder()
                .id(model.getId())
                .productoId(model.getProductoId())
                .cantidad(model.getCantidad())
                .precioUnitario(model.getPrecioUnitario())
                .subtotal(model.getSubtotal())
                .build();
    }

    public static DetalleVentaModel toModel(DetalleVentaRequest request) {
        return DetalleVentaModel.builder()
                .productoId(request.getProductoId())
                .cantidad(request.getCantidad())
                .precioUnitario(request.getPrecioUnitario())
                .build();
    }

    public DetalleVentaCompletaFeign toDetalleVentaCompletaFeign(
            DetalleVentaModel model) {

        ProductoResponse producto = productoFeignClient
                .obtenerProductoPorId(model.getProductoId())
                .getData();

        return DetalleVentaCompletaFeign.builder()
                .id(model.getId())
                .productoId(model.getProductoId())
                .nombreProducto(producto.getNombreProducto())
                .cantidad(model.getCantidad())
                .precioUnitario(model.getPrecioUnitario())
                .subtotal(model.getSubtotal())
                .build();
    }

}
