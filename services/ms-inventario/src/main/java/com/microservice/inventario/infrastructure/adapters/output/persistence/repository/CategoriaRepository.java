package com.microservice.inventario.infrastructure.adapters.output.persistence.repository;

import com.microservice.inventario.infrastructure.adapters.output.persistence.entity.CategoriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CategoriaRepository extends JpaRepository<CategoriaEntity, UUID> {
}
