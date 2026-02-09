package com.microservice.venta.infrastructure.adapters.input.rest.model.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PagoRequest {

    String metodoPago;

}
