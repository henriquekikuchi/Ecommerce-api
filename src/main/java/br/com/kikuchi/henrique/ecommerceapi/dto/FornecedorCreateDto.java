package br.com.kikuchi.henrique.ecommerceapi.dto;

import javax.validation.constraints.NotNull;

public record FornecedorCreateDto (
        @NotNull(message = "A descrição do fornecedor não pode ser nula.")
        String descricao
) {}
