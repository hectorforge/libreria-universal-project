package com.microservice.venta.infrastructure.adapters.output.persistence.repository;

import com.microservice.venta.infrastructure.adapters.output.persistence.entity.PagoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PagoRepository extends JpaRepository<PagoEntity, Integer> {

    @Query("SELECT p FROM PagoEntity p LEFT JOIN FETCH p.venta WHERE p.activo = true")
    List<PagoEntity> listarPagosActivos();

    @Query("SELECT p FROM PagoEntity p LEFT JOIN FETCH p.venta WHERE p.activo = true AND p.id = :id")
    Optional<PagoEntity> obtenerPagoActivoPorId(@Param("id") Integer id);

}
