package com.microservice.venta.infrastructure.adapters.output.persistence.repository;

import com.microservice.venta.infrastructure.adapters.input.rest.model.response.VentaReactivoResponse;
import com.microservice.venta.infrastructure.adapters.output.persistence.entity.VentaEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface VentaRepository extends JpaRepository<VentaEntity, UUID> {

    // Obtener una venta por su ID, incluyendo los detalles asociados
    @EntityGraph(attributePaths = "detalles")
    @Query("SELECT v FROM VentaEntity v LEFT JOIN FETCH v.detalles WHERE v.id = :id")
    Optional<VentaEntity> obtenerVentaPorId(@Param("id") UUID id);

    // Listar todas las ventas con sus detalles asociados
    @EntityGraph(attributePaths = "detalles")
    @Query("SELECT v FROM VentaEntity v LEFT JOIN FETCH v.detalles")
    List<VentaEntity> listarVentas();

    // Obtener ventas por ID de cliente, incluyendo los detalles asociados
    @EntityGraph(attributePaths = "detalles")
    @Query("SELECT v FROM VentaEntity v LEFT JOIN FETCH v.detalles WHERE v.clienteId = :idCliente")
    List<VentaEntity> obtenerVentaPorIdCliente(@Param("idCliente") UUID idCliente);

    // Obtener ventas dentro de un rango de fechas, incluyendo los detalles asociados
    @EntityGraph(attributePaths = "detalles")
    @Query("SELECT v FROM VentaEntity v LEFT JOIN FETCH v.detalles " +
            "WHERE v.fecha BETWEEN :fechaDesde AND :fechaHasta")
    List<VentaEntity> obtenerVentasPorFechas(@Param("fechaDesde") LocalDate fechaDesde,
                                             @Param("fechaHasta") LocalDate fechaHasta);

    // Contar ventas realizadas en un año específico
    @Query("""
        SELECT COUNT(v)
        FROM VentaEntity v
        WHERE EXTRACT(YEAR FROM v.fecha) = :anio
    """)
    long contarVentasPorAnio(@Param("anio") int anio);

//    // Método reactivo para obtener una venta por su ID, incluyendo los detalles asociados
//    @EntityGraph(attributePaths = "detalles")
//    @Query("SELECT v FROM VentaEntity v LEFT JOIN FETCH v.detalles WHERE v.id = :id")
//    Mono<VentaReactivoResponse> obtenerVentaReactivoPorId(@Param("id") UUID id);
}
