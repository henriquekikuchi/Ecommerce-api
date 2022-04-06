package br.com.kikuchi.henrique.ecommerceapi.service;

import br.com.kikuchi.henrique.ecommerceapi.entity.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;

public interface ProdutoService {

    Page<Produto> getAllProdutos(Pageable pageable);
    Page<Produto> getAllProdutosByCategoria(Pageable pageable, String descricaoCategoria);
    Produto getProdutoById(Long id);
    Produto saveProduto(Produto produto);
    void updatePrecoDoProdutoById(Long id, BigDecimal preco);
    void deleteProdutoById(Long id);
}
