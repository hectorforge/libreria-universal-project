package com.microservice.venta.infrastructure.adapters.output.persistence.repository;

import com.microservice.venta.infrastructure.adapters.output.persistence.entity.DetalleVentaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetalleVentaRepository extends JpaRepository<DetalleVentaEntity, Integer> {
}
