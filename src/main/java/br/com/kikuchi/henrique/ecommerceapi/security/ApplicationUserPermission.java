package br.com.kikuchi.henrique.ecommerceapi.entity;

public enum ApplicationUserPermission {
    PRODUTO_READ("produto:read"),
    PRODUTO_WRITE("produto:write"),
    FORNECEDOR_READ("fornecedor:read"),
    FORNECEDOR_WRITE("fornecedor:write"),
    CATEGORIA_READ("categoria:read"),
    CATEGORIA_WRITE("categoria:write")
}
