package com.microservice.venta.infrastructure.adapters.output.persistence.entity;

import com.microservice.venta.domain.enums.Estado;
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
@Table(name = "ventas")
@Entity
public class VentaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "venta_id")
    private UUID id;
    private String codigo;

    @Column(name = "cliente_id")
    private UUID clienteId;
    private Double total;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_venta")
    private LocalDate fecha;
    private boolean activo;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado")
    private Estado estado;

    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<DetalleVentaEntity> detalles = new ArrayList<>();

}
