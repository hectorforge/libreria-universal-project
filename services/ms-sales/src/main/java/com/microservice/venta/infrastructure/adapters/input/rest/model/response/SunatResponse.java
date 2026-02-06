package com.microservice.venta.infrastructure.adapters.input.rest.model.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SunatResponse {

    private String ruc;
    private String razonSocial;
    private String nombreComercial;
    private String estado;
    private String condicion;
    private String direccion;
    private String departamento;
    private String provincia;
    private String distrito;
    private String ubigeo;

}
