package com.microservice.venta.infrastructure.adapters.output.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.UUID;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pagos")
@Entity
public class PagoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pago_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "venta_id", nullable = false)
    private VentaEntity venta;

    private Double monto;

    @Column(name = "metodo_pago")
    private String metodoPago;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_pago")
    private LocalDate fechaPago;

    private boolean activo;
    private String estado;

}
