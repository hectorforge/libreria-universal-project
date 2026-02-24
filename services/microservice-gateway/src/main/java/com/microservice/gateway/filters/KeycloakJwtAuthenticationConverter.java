package com.microservice.gateway.filters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.*;
import reactor.core.publisher.Mono;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class KeycloakJwtAuthenticationConverter
        implements Converter<Jwt, Mono<AbstractAuthenticationToken>> {

    @Override
    public Mono<AbstractAuthenticationToken> convert(Jwt jwt) {

        JwtGrantedAuthoritiesConverter defaultConverter =
                new JwtGrantedAuthoritiesConverter();

        Collection<GrantedAuthority> authorities =
                Stream.concat(
                        defaultConverter.convert(jwt).stream(),
                        extractResourceRoles(jwt).stream()
                ).collect(Collectors.toSet());

        return Mono.just(new JwtAuthenticationToken(jwt, authorities));
    }

    private Collection<? extends GrantedAuthority> extractResourceRoles(Jwt jwt) {

        Map<String, Object> resourceAccess = jwt.getClaim("resource_access");

        if (resourceAccess == null || !resourceAccess.containsKey("account")) {
            return Collections.emptyList();
        }

        Map<String, Object> account =
                (Map<String, Object>) resourceAccess.get("account");

        List<String> roles =
                (List<String>) account.get("roles");

        if (roles == null) return Collections.emptyList();

        return roles.stream()
                .map(role -> "ROLE_" + role.replace("-", "_"))
                .map(SimpleGrantedAuthority::new)
                .toList();
    }
}