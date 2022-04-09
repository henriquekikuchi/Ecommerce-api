package br.com.kikuchi.henrique.ecommerceapi.security;

public enum ApplicationUserPermission {
    PRODUTO_READ("produto:read"),
    PRODUTO_WRITE("produto:write"),
    FORNECEDOR_READ("fornecedor:read"),
    FORNECEDOR_WRITE("fornecedor:write"),
    CATEGORIA_READ("categoria:read"),
    CATEGORIA_WRITE("categoria:write"),
    USER_READ("user:read"),
    USER_WRITE("user:write"),
    PEDIDO_READ("pedido:read"),
    PEDIDO_WRITE("pedido:write");

    private final String permissao;

    ApplicationUserPermission (String permissao){
        this.permissao = permissao;
    }

    public String getPermissao() {
        return permissao;
    }
}
