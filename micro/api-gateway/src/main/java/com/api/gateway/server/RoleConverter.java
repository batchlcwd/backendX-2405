package com.api.gateway.server;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class RoleConverter implements Converter<Jwt, Collection<GrantedAuthority>> {
    @Override
    public Collection<GrantedAuthority> convert(Jwt source) {
        var realAccess = (Map<String,Object>)source.getClaims().get("realm_access");
        var roles=(List<String>)realAccess.get("roles");
        List<GrantedAuthority> authorities = roles.stream().map(role ->
                new SimpleGrantedAuthority("ROLE_" + role.toUpperCase())).collect(Collectors.toList());
        return authorities;
    }
}
