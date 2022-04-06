package br.com.kikuchi.henrique.ecommerceapi.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static br.com.kikuchi.henrique.ecommerceapi.security.ApplicationUserPermission.*;

public enum ApplicationUserRole {
    CLIENT(Sets.newHashSet(PRODUTO_READ, CATEGORIA_READ)),
    ADMIN(Sets.newHashSet(PRODUTO_READ,
            PRODUTO_WRITE,
            FORNECEDOR_READ,
            FORNECEDOR_WRITE,
            CATEGORIA_READ,
            CATEGORIA_WRITE));

    private final Set<ApplicationUserPermission> permissoes;

    ApplicationUserRole(Set<ApplicationUserPermission> permissoes){
        this.permissoes = permissoes;
    }

    public Set<ApplicationUserPermission> getPermissoes() {
        return permissoes;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> permissoes = getPermissoes().stream()
                .map(permissao -> new SimpleGrantedAuthority(permissao.getPermissao()))
                .collect(Collectors.toSet());
        permissoes.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissoes;
    }
}
