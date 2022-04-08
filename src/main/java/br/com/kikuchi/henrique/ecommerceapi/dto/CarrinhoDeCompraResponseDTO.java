package br.com.kikuchi.henrique.ecommerceapi.dto;

import br.com.kikuchi.henrique.ecommerceapi.entity.CarrinhoDeCompraStatus;
import br.com.kikuchi.henrique.ecommerceapi.entity.Produto;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface CarrinhoDeCompraResponseDTO {

    Long getId();
    CarrinhoDeCompraStatus getStatus();
    UserResponseDTO getCliente();
    List<ProdutoResponseDTO> getProdutos();
}
