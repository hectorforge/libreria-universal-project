package com.microservice.inventario.infrastructure.adapters.input.rest.model.response;
import lombok.*;

import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InventarioProductoResponse {

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
