package br.com.kikuchi.henrique.ecommerceapi.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public record ProdutoCreateDto(
        @NotNull(message = "O nome do produto não pode ser nulo.")
        String nome,
        @Min(value = 0, message = "O preço do produto deve ser igual ou maior que zero.")
        BigDecimal preco,
        @NotNull(message = "O nome do fornecedor não pode ser nulo.")
        String fornecedorNome,
        @NotNull(message = "A categoria não pode ser nula.")
        String categoriaNome
) {}
