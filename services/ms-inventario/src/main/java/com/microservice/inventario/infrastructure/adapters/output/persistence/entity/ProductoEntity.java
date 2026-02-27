package com.microservice.inventario.infrastructure.adapters.output.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@Table(name = "productos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_producto")
    private UUID id;

    private String codigo;
    private String nombre;
    private String descripcion;
    private Double precioActual;

    @ManyToOne
    @JoinColumn(name = "id_categoria", nullable = false)
    private CategoriaEntity categoria;

    private boolean estado;
    private String urlImagen;
    @Column(name = "fecha_creacion", nullable = false, updatable = false)
    private LocalDateTime fechaCreacion;

    @PrePersist
    public void prePersist() {
        this.fechaCreacion = LocalDateTime.now();
    }
}
