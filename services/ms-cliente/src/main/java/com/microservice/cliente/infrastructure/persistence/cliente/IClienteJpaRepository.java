package com.microservice.cliente.infrastructure.persistence.cliente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface IClienteJpaRepository extends JpaRepository<ClienteEntity, UUID> {
    @Query(
            value = "SELECT * FROM tb_clientes c " +
                    "WHERE c.is_deleted = false " +
                    "AND (:apellidos IS NULL OR c.apellidos ILIKE '%' || :apellidos || '%') " +
                    "AND (:nombres IS NULL OR c.nombre ILIKE '%' || :nombres || '%') " +
                    "AND (:email IS NULL OR c.email = :email) " +
                    "AND (:tipoCliente IS NULL OR c.tipo_persona = :tipoCliente)",
            countQuery = "SELECT COUNT(*) FROM tb_clientes c " +
                    "WHERE c.is_deleted = false " +
                    "AND (:apellidos IS NULL OR c.apellidos ILIKE '%' || :apellidos || '%') " +
                    "AND (:nombres IS NULL OR c.nombre ILIKE '%' || :nombres || '%') " +
                    "AND (:email IS NULL OR c.email = :email) " +
                    "AND (:tipoCliente IS NULL OR c.tipo_persona = :tipoCliente)",
            nativeQuery = true
    )
    Page<ClienteEntity> listarTodos(
            @Param("apellidos") String apellidos,
            @Param("nombres") String nombres,
            @Param("email") String email,
            @Param("tipoCliente") String tipoCliente,
            Pageable pageable
    );

    Optional<ClienteEntity> findByKeycloakId(String keycloackId);
}
