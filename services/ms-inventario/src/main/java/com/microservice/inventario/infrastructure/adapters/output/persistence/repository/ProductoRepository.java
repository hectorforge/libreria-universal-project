package com.microservice.inventario.infrastructure.adapters.output.persistence.repository;

import com.microservice.inventario.infrastructure.adapters.output.persistence.entity.ProductoEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductoRepository extends JpaRepository<ProductoEntity, UUID> {
    List<ProductoEntity> findByCategoriaId(UUID categoriaId);

    @EntityGraph(attributePaths = "categoria")//trae la tabla mapeada de manera robusta
    @Query("""
    SELECT p FROM ProductoEntity p
    LEFT JOIN FETCH p.categoria
""")
    List<ProductoEntity> listarProductos();

}
