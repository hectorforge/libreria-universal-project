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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
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
public class ClienteRestController {

    private final IClienteServiceInPort clienteServiceInPort;
    private final ClienteDomainDtoMapper mapper;

    @Operation(
            summary = "Crear un cliente",
            description = "Crea un nuevo cliente en el sistema. Devuelve el cliente creado o un error si falla la operación.")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
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
    @PostMapping("/registro-inicial")
    public OperationResult<ClienteResponse> sync(Authentication authentication) {

        JwtAuthenticationToken jwtAuth = (JwtAuthenticationToken) authentication;
        Jwt jwt = jwtAuth.getToken();

        Cliente cliente = new Cliente();
        cliente.setKeycloakId(jwt.getClaimAsString("sub"));
        cliente.setNombre(jwt.getClaimAsString("given_name"));
        cliente.setApellidos(jwt.getClaimAsString("family_name"));
        cliente.setEmail(jwt.getClaimAsString("email"));

        // Mapeo seguro de Enums
        try {
            String tipoPersona = jwt.getClaimAsString("type_person");
            if (tipoPersona != null) cliente.setTipoPersona(TipoCliente.valueOf(tipoPersona.toUpperCase()));

            String tipoDoc = jwt.getClaimAsString("type_document");
            if (tipoDoc != null) cliente.setTipoDocumento(TipoDocumento.valueOf(tipoDoc.toUpperCase()));
        } catch (IllegalArgumentException e) {
            // Si el valor del token no coincide con el Enum, podrías registrar un log o asignar null
        }

        // Lógica de extracción de roles del Realm
        Map<String, Object> realmAccess = jwt.getClaimAsMap("realm_access");
        List<String> roles = (realmAccess != null && realmAccess.get("roles") != null)
                ? (List<String>) realmAccess.get("roles")
                : Collections.emptyList();

        // Prioridad de roles para el campo "rol" de tu entidad
        if (roles.contains("ADMINISTRADOR")) {
            cliente.setRol("ADMINISTRADOR");
        } else if (roles.contains("VENDEDOR")) {
            cliente.setRol("VENDEDOR");
        } else {
            cliente.setRol("USUARIO");
        }

        // 2. Ejecución del Caso de Uso (Application Layer)
        OperationResult<Cliente> result = clienteServiceInPort.syncWithKeycloak(cliente);

        // 3. Mapeo de Dominio a Respuesta (DTO)
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
    public OperationResult<Boolean> eliminar(@PathVariable UUID id) {
        return clienteServiceInPort.deleteById(id);
    }

    @Operation(
            summary = "Listar clientes con filtros y paginación",
            description = "Lista clientes según filtros opcionales (apellidos, nombre, email, tipo de cliente) y permite paginación. Devuelve un PagedResult con los clientes encontrados o un error en caso de fallo.")
    @GetMapping
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

    @GetMapping("/dummy")
    public String dummy(){
        log.info("Paso por el dummy");
        return "Soy un dummy";
    }
}