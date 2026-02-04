package com.microservice.venta.infrastructure.adapters.output.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "detalle_facturas")
@Entity
public class DetalleFacturaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer cantidad;
    private String descripcion;

    @Column(name = "valor_unitario")
    private Double valorUnitario;
    private Double subtotal;

    @ManyToOne
    @JoinColumn(name = "facturacion_id", nullable = false)
    @JsonIgnore
    private FacturacionEntity facturacion;
}
