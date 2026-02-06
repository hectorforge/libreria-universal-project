package com.microservice.inventario.infrastructure.adapters.output.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "inventario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventarioEntity {

    @Id

    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private UUID productoId;

    private Integer stockActual;
    private Integer stockMinimo;
}