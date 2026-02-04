package com.microservice.venta.infrastructure.adapters.output.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "facturas")
@Entity
public class FacturacionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "facturacion_id")
    private UUID id;
    private String numeroFactura;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_factura")
    private LocalDate fechaFactura;

    @ManyToOne
    @JoinColumn(name = "venta_id", nullable = false)
    private VentaEntity venta;

    @Column(name = "metodo_pago")
    private String metodoPago;
    private Double total;
    private Double subtotal;
    private Double impuestos;
    private boolean activo;
    private String estado;

    @OneToMany(mappedBy = "facturacion", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    List<DetalleFacturaEntity> detalles = new ArrayList<>();

}
