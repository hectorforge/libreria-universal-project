package com.microservice.venta.infrastructure.adapters.output.persistence.repository;

import com.microservice.venta.infrastructure.adapters.output.persistence.entity.FacturacionEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FacturacionRepository extends JpaRepository<FacturacionEntity, UUID> {

    // Consulta para listar todas las facturas activas
    @EntityGraph(attributePaths = "detalles")
    @Query("SELECT f FROM FacturacionEntity f LEFT JOIN FETCH f.detalles WHERE f.activo = TRUE ")
    List<FacturacionEntity> listarFacturasTrue();

    // Consulta para obtener factura por ID
    @EntityGraph(attributePaths = "detalles")
    @Query("SELECT f FROM FacturacionEntity f LEFT JOIN FETCH f.detalles WHERE f.id = :id")
    Optional <FacturacionEntity> obtenerFacturaPorId (@Param("id") UUID id);

    // Consulta para obtener factura por número de factura
    @EntityGraph(attributePaths = "detalles")
    @Query("SELECT f FROM FacturacionEntity f LEFT JOIN FETCH f.detalles WHERE f.numeroFactura = :numFactura")
    Optional <FacturacionEntity> obtenerFacturaPorCodigoFactura(@Param("numFactura") String numFactura);

    // Consulta para listar facturas por rango de fechas
    @EntityGraph(attributePaths = "detalles")
    @Query("SELECT f FROM FacturacionEntity f LEFT JOIN FETCH f.detalles WHERE f.fechaFactura BETWEEN :fechaDesde AND :fechaHasta")
    List<FacturacionEntity> listarFacturasPorFechas(@Param("fechaDesde") LocalDate fechaDesde,
                                                    @Param("fechaHasta") LocalDate fechaHasta);

    // Consulta para obtener factura por ID de venta
    @EntityGraph(attributePaths = "detalles")
    @Query("SELECT f FROM FacturacionEntity f LEFT JOIN FETCH f.detalles WHERE f.venta.id = :idVenta")
    Optional<FacturacionEntity> obtenerFacturaPorIdVenta(@Param("idVenta") UUID idVenta);

    @Query("""
        SELECT COUNT(v)
        FROM FacturacionEntity v
        WHERE EXTRACT(YEAR FROM v.fechaFactura) = :anio
    """)
    long contarFacturasPorAnio(@Param("anio") int anio);
}
