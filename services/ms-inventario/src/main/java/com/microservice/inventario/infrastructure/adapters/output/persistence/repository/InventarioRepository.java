package com.microservice.inventario.infrastructure.adapters.output.persistence.repository;

import com.microservice.inventario.infrastructure.adapters.output.persistence.entity.InventarioEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface InventarioRepository extends JpaRepository<InventarioEntity, UUID> {

    @Query("SELECT i FROM InventarioEntity i WHERE i.productoId.id = :productoId")
    Optional<InventarioEntity> findByProductoId(@Param("productoId") UUID productoId);

    @EntityGraph(attributePaths = {"productoId", "productoId.categoria"})
    @Query("SELECT i FROM InventarioEntity i " +
            "LEFT JOIN FETCH i.productoId p " +
            "LEFT JOIN FETCH i.productoId.categoria c " +
            "WHERE i.productoId.id = :productoId")
    Optional<InventarioEntity> findByProductoIdWithProductoAndCategoria(@Param("productoId") UUID productoId);
}
