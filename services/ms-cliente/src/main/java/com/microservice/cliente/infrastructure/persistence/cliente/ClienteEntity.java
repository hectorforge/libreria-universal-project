package com.microservice.cliente.infrastructure.persistence.cliente;

import com.microservice.cliente.domain.cliente.Cliente;
import com.microservice.cliente.domain.common.TipoCliente;
import com.microservice.cliente.domain.common.TipoDocumento;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "tb_clientes",indexes = {
        @Index(name = "idx_cliente_email", columnList = "email"),
        @Index(name = "idx_cliente_rol", columnList = "rol"),
        @Index(name = "idx_cliente_tipo_persona", columnList = "tipo_persona"),
        @Index(name = "idx_cliente_tipo_documento", columnList = "tipo_documento")
})
public class ClienteEntity extends Cliente {
    // Campos de BaseEntity
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "date_created", nullable = false)
    private LocalDateTime dateCreated;

    @Column(name = "date_updated", nullable = false)
    private LocalDateTime dateUpdated;

    @Column(name = "created_by", length = 50)
    private String createdBy;

    @Column(name = "updated_by", length = 50)
    private String updatedBy;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;

    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted;

    // Campos de Cliente
    @Column(name = "keycloak_id", length = 50)
    private String keycloakId;

    @Column(name = "apellidos", length = 100, nullable = false)
    private String apellidos;

    @Column(name = "nombre", length = 100, nullable = false)
    private String nombre;

    @Column(name = "email", length = 150, nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_persona", length = 20, nullable = false)
    private TipoCliente tipoPersona;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_documento", length = 20)
    private TipoDocumento tipoDocumento;

    @Column(name = "rol", length = 50)
    private String rol;
}
