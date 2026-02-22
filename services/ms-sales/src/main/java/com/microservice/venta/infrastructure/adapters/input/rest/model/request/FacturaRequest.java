package com.microservice.venta.infrastructure.adapters.input.rest.model.request;

import lombok.*;

import java.util.UUID;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FacturaRequest {

    private UUID idVenta;

}
