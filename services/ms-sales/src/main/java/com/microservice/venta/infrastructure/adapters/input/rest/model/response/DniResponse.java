package com.microservice.venta.infrastructure.adapters.input.rest.model.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DniResponse {

    private boolean success;
    private String dni;
    private String nombres;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private Integer codVerifica;
    private String codVerificaLetra;

}
