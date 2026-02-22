package com.microservice.venta.domain.model;

import com.microservice.venta.domain.enums.Estado;
import com.microservice.venta.domain.exception.VentaNotException;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VentaModel {

    private UUID id;
    private String codigo;
    private UUID clienteId;
    private Double total;
    private LocalDate fecha;
    private boolean activo;
    private Estado estado;

    private List<DetalleVentaModel> detalles;

    public void validar() {

//        if (codigo == null || codigo.isBlank()) {
//            throw new VentaNotException("El código de la venta es obligatorio.");
//        }
//
//        if (clienteId == null) {
//            throw new VentaNotException("El cliente es obligatorio.");
//        }
//
//        if (fecha == null) {
//            throw new VentaNotException("La fecha de la venta es obligatoria.");
//        }
//
//        if (estado == null) {
//            throw new VentaNotException("El estado de la venta es obligatorio.");
//        }
//
//        if (total == null) {
//            throw new VentaNotException("El total de la venta es obligatorio.");
//        }
//
//        if (total < 0) {
//            throw new VentaNotException("El total no puede ser negativo.");
//        }
//
//        if (detalles == null || detalles.isEmpty()) {
//            throw new VentaNotException("La venta debe tener al menos un detalle.");
//        }
    }
}
