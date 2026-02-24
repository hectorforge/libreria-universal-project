package com.microservice.venta.infrastructure.adapters.output.client.response;

import lombok.*;

import java.util.UUID;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductoResponse {

    private UUID idProducto;
    private String codigoProducto;
    private String nombreProducto;
    private String descripcionProducto;
    private Double precioActualProducto;
    private Integer stockActual;
    private Integer stockMinimo;
    private UUID idCategoria;
    private String nombreCategoria;
    private String descripcionCategoria;


}
