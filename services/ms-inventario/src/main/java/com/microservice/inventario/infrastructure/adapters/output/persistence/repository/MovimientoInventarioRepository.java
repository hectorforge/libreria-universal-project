package com.microservice.inventario.infrastructure.adapters.output.persistence.repository;

import com.microservice.inventario.infrastructure.adapters.output.persistence.entity.MovimientoInventarioEntity;
import com.microservice.inventario.infrastructure.adapters.output.persistence.entity.ProductoEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface MovimientoInventarioRepository extends JpaRepository<MovimientoInventarioEntity, UUID> {

    //  LISTAR TODOS CON PRODUCTO
    @EntityGraph(attributePaths = "producto")
    @Query("""
        SELECT m
        FROM MovimientoInventarioEntity m
        JOIN FETCH m.producto
    """)
    List<MovimientoInventarioEntity> listarTodosConProducto();

    //  LISTAR POR PRODUCTO CON PRODUCTO CARGADO
    @EntityGraph(attributePaths = "producto")
    @Query("""
        SELECT m
        FROM MovimientoInventarioEntity m
        JOIN FETCH m.producto
        WHERE m.producto.id = :productoId
    """)
    List<MovimientoInventarioEntity> listarPorProductoIdConProducto(UUID productoId);


    List<MovimientoInventarioEntity> findByFechaBetween(
            LocalDateTime fechaInicio,
            LocalDateTime fechaFin
    );
}


