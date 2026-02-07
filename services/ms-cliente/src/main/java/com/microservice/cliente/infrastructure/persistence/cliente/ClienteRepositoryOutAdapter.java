package com.microservice.cliente.infrastructure.persistence.cliente;

import com.libreriauniversal.PagedResult;
import com.microservice.cliente.domain.cliente.Cliente;
import com.microservice.cliente.domain.cliente.ports.out.IClienteRepositoryOutPort;
import com.microservice.cliente.domain.cliente.utils.ClienteFiltro;
import com.microservice.cliente.infrastructure.persistence.mappers.ClienteDomainEntityMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class ClienteRepositoryOutAdapter implements IClienteRepositoryOutPort {
    private final IClienteJpaRepository clienteJpaRepository;
    private final ClienteDomainEntityMapper mapper;

    @Override
    @Transactional
    public Cliente save(Cliente cliente) {
        ClienteEntity entity = mapper.toEntity(cliente);
        if (entity.getId() == null) {
            entity.setDateCreated(LocalDateTime.now());
        }
        entity.setDateUpdated(LocalDateTime.now());
        entity.setActive(true);
        entity.setDeleted(false);
        ClienteEntity saved = clienteJpaRepository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public boolean deleteById(UUID id) {
        return clienteJpaRepository.findById(id)
                .map(entity -> {
                    entity.setDeleted(true);
                    entity.setDateUpdated(LocalDateTime.now());
                    clienteJpaRepository.save(entity);
                    return true;
                }).orElse(false);
    }

    @Override
    public Optional<Cliente> findById(UUID id) {
        return clienteJpaRepository.findById(id)
                .filter(c->!c.isDeleted())
                .map(mapper::toDomain);
    }

    @Override
    public PagedResult<Cliente> findAll(ClienteFiltro filtros, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ClienteEntity> pageResult = clienteJpaRepository.listarTodos(
                filtros.apellidos(),
                filtros.nombres(),
                filtros.email(),
                filtros.tipoCliente().toString(),
                pageable);

        List<Cliente> items = pageResult.getContent().stream()
                .map(mapper::toDomain).toList();

        return new PagedResult<>(
                items,
                pageResult.getNumber(),
                pageResult.getSize(),
                pageResult.getTotalElements(),
                pageResult.getTotalPages(),
                pageResult.isFirst(),
                pageResult.isLast(),
                pageResult.hasNext(),
                pageResult.hasPrevious(),
                pageResult.isEmpty()
        );
    }
}
