package com.microservice.cliente.application.cliente;

import com.libreriauniversal.ErrorCatalog;
import com.libreriauniversal.OperationResult;
import com.libreriauniversal.PagedResult;
import com.microservice.cliente.domain.cliente.Cliente;
import com.microservice.cliente.domain.cliente.ports.in.IClienteServiceInPort;
import com.microservice.cliente.domain.cliente.ports.out.IClienteRepositoryOutPort;
import com.microservice.cliente.domain.cliente.utils.ClienteFiltro;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClienteService implements IClienteServiceInPort {
    private IClienteRepositoryOutPort clienteRepository;

    @Override
    public OperationResult<Cliente> create(Cliente cliente) {
        try {
            Cliente guardado = clienteRepository.save(cliente);
            return OperationResult.success(guardado);
        } catch (Exception e) {
            return OperationResult.failureSingle(
                    ErrorCatalog.CLIENTE_SAVE_ERROR.getErrorCode(),
                    ErrorCatalog.CLIENTE_SAVE_ERROR.getErrorMessage()
            );
        }
    }

    @Override
    public OperationResult<Cliente> update(UUID id, Cliente cliente) {
        try {
            Optional<Cliente> existenteOpt = clienteRepository.findById(id);

            if (existenteOpt.isEmpty()) {
                return OperationResult.failureSingle(
                        ErrorCatalog.CLIENTE_NOT_FOUND.getErrorCode(),
                        ErrorCatalog.CLIENTE_NOT_FOUND.getErrorMessage()
                );
            }

            Cliente existente = existenteOpt.get();
            cliente.setId(id);
            cliente.setDateCreated(existente.getDateCreated());
            cliente.setDateUpdated(LocalDateTime.now());

            Cliente actualizado = clienteRepository.save(cliente);
            return OperationResult.success(actualizado);

        } catch (Exception e) {
            return OperationResult.failureSingle(
                    ErrorCatalog.CLIENTE_UPDATE_ERROR.getErrorCode(),
                    ErrorCatalog.CLIENTE_UPDATE_ERROR.getErrorMessage()
            );
        }
    }

    @Override
    public OperationResult<Boolean> deleteById(UUID id) {
        try {
            boolean eliminado = clienteRepository.deleteById(id);
            if (eliminado) {
                return OperationResult.success(true);
            } else {
                return OperationResult.failureSingle(
                        ErrorCatalog.CLIENTE_NOT_FOUND.getErrorCode(),
                        ErrorCatalog.CLIENTE_NOT_FOUND.getErrorMessage()
                );
            }
        } catch (Exception e) {
            return OperationResult.failureSingle(
                    ErrorCatalog.CLIENTE_DELETE_ERROR.getErrorCode(),
                    ErrorCatalog.CLIENTE_DELETE_ERROR.getErrorMessage()
            );
        }
    }

    @Override
    public OperationResult<Cliente> getClienteById(UUID id) {
        try {
            return clienteRepository.findById(id)
                    .map(OperationResult::success)
                    .orElseGet(() -> OperationResult.failureSingle(
                            ErrorCatalog.CLIENTE_NOT_FOUND.getErrorCode(),
                            ErrorCatalog.CLIENTE_NOT_FOUND.getErrorMessage()
                    ));
        } catch (Exception e) {
            return OperationResult.failureSingle(
                    ErrorCatalog.GENERIC_ERROR.getErrorCode(),
                    "Error al obtener cliente: " + e.getMessage()
            );
        }
    }

    @Override
    public OperationResult<PagedResult<Cliente>> getAllClientes(ClienteFiltro filtros, int page, int size) {
        try {
            PagedResult<Cliente> resultado = clienteRepository.findAll(filtros, page, size);
            return OperationResult.success(resultado);
        } catch (Exception e) {
            return OperationResult.failureSingle(
                    ErrorCatalog.CLIENTE_LIST_ERROR.getErrorCode(),
                    ErrorCatalog.CLIENTE_LIST_ERROR.getErrorMessage()
            );
        }
    }
}
