package com.microservice.cliente.infrastructure.security;

import com.microservice.cliente.domain.cliente.Cliente;
import com.microservice.cliente.domain.common.TipoCliente;
import com.microservice.cliente.domain.common.TipoDocumento;
import com.microservice.cliente.infrastructure.security.dto.UsuarioInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Componente que extrae información del usuario desde un token JWT de Keycloak
 * y mapea esa información a la entidad Cliente para sincronización con el sistema interno.
 */
@Component
public class JwtUserExtractor {

    private static final Logger log = LoggerFactory.getLogger(JwtUserExtractor.class);

    /**
     * Roles válidos dentro del sistema.
     * Solo estos roles serán asignables a la entidad Cliente.
     */
    private static final Set<String> VALID_ROLES = Set.of(
            "ROLE_USUARIO",
            "ROLE_VENDEDOR",
            "ROLE_ALMACENERO",
            "ROLE_ADMINISTRADOR"
    );

    /**
     * Extrae información básica de un usuario desde el token JWT contenido en el Authentication.
     *
     * @param auth Objeto Authentication proveniente del contexto de Spring Security
     * @return UsuarioInfo con datos del usuario y roles filtrados. Retorna null si el auth no es JwtAuthenticationToken.
     */
    public UsuarioInfo extract(Authentication auth) {
        if (auth == null || !(auth instanceof JwtAuthenticationToken)) {
            log.warn("Authentication inválido o no es JwtAuthenticationToken. auth={}", auth);
            return null;
        }

        JwtAuthenticationToken token = (JwtAuthenticationToken) auth;
        var jwt = token.getToken();

        List<String> roles = token.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .filter(VALID_ROLES::contains)
                .collect(Collectors.toList());

        String id = jwt.getSubject();
        String email = jwt.getClaimAsString("email");
        String nombre = jwt.getClaimAsString("given_name");
        String apellido = jwt.getClaimAsString("family_name");
        String tipoPersona = jwt.getClaimAsString("type_person");
        String tipoDocumento = jwt.getClaimAsString("type_document");
        String numeroDocumento = jwt.getClaimAsString("number_document");

        log.info("Usuario extraído: id={}, email={}, roles={}, fecha={}", id, email, roles, LocalDateTime.now());

        return new UsuarioInfo(
                id,
                email,
                nombre,
                apellido,
                nombre + " " + apellido,
                tipoPersona,
                tipoDocumento,
                numeroDocumento,
                roles
        );
    }

    /**
     * Convierte un Authentication a la entidad Cliente del sistema interno,
     * asignando solo roles válidos y mapeando enums de forma segura.
     *
     * @param auth Authentication proveniente del contexto de Spring Security
     * @return Cliente con información extraída del JWT. Retorna null si el auth es inválido.
     */
    public Cliente toCliente(Authentication auth) {
        UsuarioInfo info = extract(auth);
        if (info == null) return null;

        Cliente cliente = new Cliente();
        cliente.setKeycloakId(info.id());
        cliente.setNombre(info.nombre());
        cliente.setApellidos(info.apellido());
        cliente.setEmail(info.email());
        cliente.setNumeroDocumento(info.numeroDocumento());

        try {
            if (info.tipoPersona() != null)
                cliente.setTipoPersona(TipoCliente.valueOf(info.tipoPersona().toUpperCase()));

            if (info.tipoDocumento() != null)
                cliente.setTipoDocumento(TipoDocumento.valueOf(info.tipoDocumento().toUpperCase()));
        } catch (IllegalArgumentException e) {
            log.warn("Valor de tipoPersona o tipoDocumento no coincide con el Enum. tipoPersona={}, tipoDocumento={}, error={}",
                    info.tipoPersona(), info.tipoDocumento(), e.getMessage());
        }

        if (!info.roles().isEmpty()) {
            String rolAsignado = info.roles().get(0);
            cliente.setRol(rolAsignado);
            log.info("Rol asignado a cliente id={} -> {}", cliente.getKeycloakId(), rolAsignado);
        } else {
            log.info("No se asignó ningún rol válido a cliente id={}", cliente.getKeycloakId());
        }

        log.info("Cliente mapeado para sincronización: id={}, email={}, fecha={}",
                cliente.getKeycloakId(), cliente.getEmail(), LocalDateTime.now());

        return cliente;
    }
}