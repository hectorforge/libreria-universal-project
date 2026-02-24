package com.microservice.inventario.infrastructure.security;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class KeycloakJwtAuthenticationConverter implements Converter<Jwt, AbstractAuthenticationToken> {

    @Override
    public AbstractAuthenticationToken convert(@NonNull Jwt source) {
        return new JwtAuthenticationToken(
                source,
                Stream.concat(
                        new JwtGrantedAuthoritiesConverter().convert(source).stream(),
                        extractRoles(source).stream()
                ).collect(Collectors.toSet())
        );
    }

    private Collection<? extends GrantedAuthority> extractRoles(Jwt jwt) {
        Set<String> allRoles = new HashSet<>();

        // 1. Extraer Realm Roles (Donde están ADMINISTRADOR, VENDEDOR, USUARIO)
        Map<String, Object> realmAccess = jwt.getClaim("realm_access");
        if (realmAccess != null && realmAccess.get("roles") instanceof List) {
            allRoles.addAll((List<String>) realmAccess.get("roles"));
        }

        // 2. Extraer Resource Roles (Opcional, lo que tenías antes)
        Map<String, Object> resourceAccess = jwt.getClaim("resource_access");
        if (resourceAccess != null && resourceAccess.get("account") instanceof Map) {
            Map<String, List<String>> account = (Map<String, List<String>>) resourceAccess.get("account");
            if (account.get("roles") != null) {
                allRoles.addAll(account.get("roles"));
            }
        }

        return allRoles.stream()
                .map(role -> "ROLE_" + role.replace("-", "_"))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}