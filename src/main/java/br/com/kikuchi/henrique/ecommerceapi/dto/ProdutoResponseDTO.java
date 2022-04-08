package br.com.kikuchi.henrique.ecommerceapi.dto;

import java.math.BigDecimal;

public interface ProdutoResponseDTO {

    String getNome();
    BigDecimal getPreco();
    FornecedorResponseDto getFornecedor();
}
