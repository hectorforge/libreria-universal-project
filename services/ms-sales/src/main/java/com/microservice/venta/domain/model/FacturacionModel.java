package com.microservice.venta.domain.model;

import com.microservice.venta.domain.exception.FacturacionNotException;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FacturacionModel {

    private UUID id;
    private String numeroFactura;
    private LocalDate fechaFactura;
    private VentaModel venta;
    private String metodoPago;
    private Double total;
    private Double subtotal;
    private Double impuestos;
    private boolean activo;
    private String estado;

    List<DetalleFacturaModel> detalles;

    public void validar() {

//        if (numeroFactura == null || numeroFactura.isBlank()) {
//            throw new FacturacionNotException("El número de factura es obligatorio.");
//        }
//
//        if (fechaFactura == null) {
//            throw new FacturacionNotException("La fecha de la factura es obligatoria.");
//        }
//
//        if (venta == null) {
//            throw new FacturacionNotException("La factura debe estar asociada a una venta.");
//        }
//
//        if (metodoPago == null || metodoPago.isBlank()) {
//            throw new FacturacionNotException("El método de pago es obligatorio.");
//        }
//
//        if (subtotal == null || subtotal < 0) {
//            throw new FacturacionNotException("El subtotal no puede ser nulo ni negativo.");
//        }
//
//        if (impuestos == null || impuestos < 0) {
//            throw new FacturacionNotException("Los impuestos no pueden ser nulos ni negativos.");
//        }
//
//        if (total == null || total < 0) {
//            throw new FacturacionNotException("El total no puede ser nulo ni negativo.");
//        }
//
//        if (detalles == null || detalles.isEmpty()) {
//            throw new FacturacionNotException("La factura debe tener al menos un detalle.");
//        }
//
//        if (estado == null || estado.isBlank()) {
//            throw new FacturacionNotException("El estado de la factura es obligatorio.");
//        }
    }
}
