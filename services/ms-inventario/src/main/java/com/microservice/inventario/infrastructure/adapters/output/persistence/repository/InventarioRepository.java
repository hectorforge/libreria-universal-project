package com.microservice.inventario.infrastructure.adapters.output.persistence.repository;

import com.microservice.inventario.infrastructure.adapters.output.persistence.entity.InventarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface InventarioRepository extends JpaRepository<InventarioEntity, UUID> {

    Optional<InventarioEntity> findByProductoId(UUID productoId);
}
