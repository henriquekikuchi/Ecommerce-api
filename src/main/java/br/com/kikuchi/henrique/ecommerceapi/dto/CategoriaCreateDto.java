package br.com.kikuchi.henrique.ecommerceapi.dto;

import javax.validation.constraints.NotNull;

public record CategoriaCreateDto(
        @NotNull(message = "A descrição da categoria não pode ser nula.")
        String descricao
) {}
