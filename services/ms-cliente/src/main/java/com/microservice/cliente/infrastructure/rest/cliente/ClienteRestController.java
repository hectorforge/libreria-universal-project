package com.microservice.cliente.infrastructure.rest.cliente;

import com.libreriauniversal.OperationResult;
import com.libreriauniversal.PagedResult;
import com.microservice.cliente.domain.cliente.Cliente;
import com.microservice.cliente.domain.cliente.ports.in.IClienteServiceInPort;
import com.microservice.cliente.domain.cliente.utils.ClienteFiltro;
import com.microservice.cliente.domain.common.TipoCliente;
import com.microservice.cliente.domain.common.TipoDocumento;
import com.microservice.cliente.infrastructure.rest.cliente.dto.ClienteRequest;
import com.microservice.cliente.infrastructure.rest.cliente.dto.ClienteResponse;
import com.microservice.cliente.infrastructure.rest.mappers.ClienteDomainDtoMapper;
import com.microservice.cliente.infrastructure.rest.cliente.validators.ActualizarClienteGrupo;
import com.microservice.cliente.infrastructure.rest.cliente.validators.CrearClienteGrupo;
import com.microservice.cliente.infrastructure.security.JwtUserExtractor;
import com.microservice.cliente.infrastructure.security.dto.UsuarioInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/clientes")
@Tag(name = "Clientes", description = "API para la gestión de clientes. Permite crear, actualizar, consultar, eliminar y listar clientes con filtros y paginación.")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class ClienteRestController {

    private final IClienteServiceInPort clienteServiceInPort;
    private final JwtUserExtractor userExtractor;
    private final ClienteDomainDtoMapper mapper;

    @Operation(
            summary = "Crear un cliente",
            description = "Crea un nuevo cliente en el sistema. Devuelve el cliente creado o un error si falla la operación.")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyRole('USUARIO','TRABAJADOR','VENDEDOR','ADMINISTRADOR')")
    public OperationResult<ClienteResponse> crear(@Validated(CrearClienteGrupo.class) @RequestBody ClienteRequest request) {
        OperationResult<Cliente> result = clienteServiceInPort.create(mapper.toDomain(request));
        if (result.isSuccess()) {
            return OperationResult.success(mapper.toResponse(result.data()));
        }
        return OperationResult.failureSingle(result.errorCode(), result.errorMessage());
    }

    @Operation(
            summary = "Sincronización inicial con Keycloak",
            description = "Recibe el token de identidad de Keycloak, extrae la información del usuario y lo registra en la base de datos local si no existe. Es el primer paso tras un login exitoso en el frontend.")
    @PostMapping("/sync")
    @PreAuthorize("hasAnyRole('USUARIO','TRABAJADOR','VENDEDOR','ADMINISTRADOR')")
    public OperationResult<ClienteResponse> sync(Authentication authentication) {

        Cliente cliente = userExtractor.toCliente(authentication);

        OperationResult<Cliente> result = clienteServiceInPort.syncWithKeycloak(cliente);

        if (result.isSuccess()) {
            return OperationResult.success(mapper.toResponse(result.data()));
        }

        return OperationResult.failureSingle(result.errorCode(), result.errorMessage());
    }

    @Operation(
            summary = "Actualizar un cliente",
            description = "Actualiza un cliente existente. Valida que el cliente exista y que el ID proporcionado coincida. Devuelve el cliente actualizado o un error si falla la operación.")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('USUARIO','TRABAJADOR','VENDEDOR','ADMINISTRADOR')")
    public OperationResult<ClienteResponse> actualizar(
            @Validated(ActualizarClienteGrupo.class) @RequestBody ClienteRequest request,
            @PathVariable UUID id) {
        OperationResult<Cliente> result = clienteServiceInPort.update(id, mapper.toDomain(request));
        if (result.isSuccess()) {
            return OperationResult.success(mapper.toResponse(result.data()));
        }
        return OperationResult.failureSingle(result.errorCode(), result.errorMessage());
    }

    @Operation(
            summary = "Obtener un cliente por ID",
            description = "Recupera un cliente utilizando su ID único. Devuelve el cliente si existe o un error indicando que no se encontró.")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('USUARIO','TRABAJADOR','VENDEDOR','ADMINISTRADOR')")
    public OperationResult<ClienteResponse> obtenerPorId(@PathVariable UUID id) {
        OperationResult<Cliente> result = clienteServiceInPort.getClienteById(id);
        if (result.isSuccess()) {
            return OperationResult.success(mapper.toResponse(result.data()));
        }
        return OperationResult.failureSingle(result.errorCode(), result.errorMessage());
    }

    @Operation(
            summary = "Eliminar un cliente",
            description = "Elimina un cliente por su ID. Devuelve true si se eliminó correctamente o un error si no existe.")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public OperationResult<Boolean> eliminar(@PathVariable UUID id) {
        return clienteServiceInPort.deleteById(id);
    }

    @Operation(
            summary = "Listar clientes con filtros y paginación",
            description = "Lista clientes según filtros opcionales (apellidos, nombre, email, tipo de cliente) y permite paginación. Devuelve un PagedResult con los clientes encontrados o un error en caso de fallo.")
    @GetMapping
    @PreAuthorize("hasAnyRole('USUARIO','TRABAJADOR','VENDEDOR','ADMINISTRADOR')")
    public OperationResult<PagedResult<ClienteResponse>> listar(
            @RequestParam(required = false) String apellidos,
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String tipoPersona,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        ClienteFiltro filtros = new ClienteFiltro(apellidos, nombre, email, tipoPersona != null ? tipoPersona : null);

        OperationResult<PagedResult<Cliente>> result = clienteServiceInPort.getAllClientes(filtros, page, size);

        if (result.isSuccess()) {
            List<ClienteResponse> content = result.data().items().stream()
                    .map(mapper::toResponse)
                    .toList();

            PagedResult<ClienteResponse> response = new PagedResult<>(
                    content,
                    result.data().pageNumber(),
                    result.data().pageSize(),
                    result.data().totalElements(),
                    result.data().totalPages(),
                    result.data().isFirst(),
                    result.data().isLast(),
                    result.data().hasNext(),
                    result.data().hasPrevious(),
                    result.data().isEmpty()
            );

            return OperationResult.success(response);
        }

        return OperationResult.failureSingle(result.errorCode(), result.errorMessage());
    }

//    @GetMapping("/me")
//    public UsuarioInfo me(Authentication auth) {
//        return userExtractor.extract(auth);
//    }

    @GetMapping("/me")
    @PreAuthorize("hasAnyRole('USUARIO','TRABAJADOR','VENDEDOR','ADMINISTRADOR')")
    public OperationResult<ClienteResponse> me(Authentication auth) {
        Cliente cliente = userExtractor.toCliente(auth); // Extrae keycloakId del JWT
        OperationResult<Cliente> result = clienteServiceInPort.findByKeycloakId(cliente.getKeycloakId());

        if(result.isSuccess() && result.data() != null){
            return OperationResult.success(mapper.toResponse(result.data()));
        } else {
            return OperationResult.failureSingle("NOT_FOUND", "Usuario no registrado en la BD interna");
        }
    }
}