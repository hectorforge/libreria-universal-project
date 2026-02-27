package com.microservice.venta.domain.model;

import com.microservice.venta.domain.exception.PagoNotException;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PagoModel {

    private Integer id;
    private VentaModel venta;
    private Double monto;
    private String metodoPago;
    private LocalDate fechaPago;
    private boolean activo;
    private String estado;

    public void validar() {

//        if (venta == null) {
//            throw new PagoNotException("El pago debe estar asociado a una venta.");
//        }
//
//        if (monto == null) {
//            throw new PagoNotException("El monto del pago es obligatorio.");
//        }
//
//        if (monto <= 0) {
//            throw new PagoNotException("El monto del pago debe ser mayor a cero.");
//        }
//
//        if (metodoPago == null || metodoPago.isBlank()) {
//            throw new PagoNotException("El método de pago es obligatorio.");
//        }
//
//        if (fechaPago == null) {
//            throw new PagoNotException("La fecha de pago es obligatoria.");
//        }
//
//        if (estado == null || estado.isBlank()) {
//            throw new PagoNotException("El estado del pago es obligatorio.");
//        }
    }

}
