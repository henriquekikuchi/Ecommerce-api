package br.com.kikuchi.henrique.ecommerceapi.service;

import br.com.kikuchi.henrique.ecommerceapi.entity.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;

import java.math.BigDecimal;


public interface ProdutoService {

    @PreAuthorize("hasAnyRole('CLIENT','ADMIN')")
    Page<Produto> getAllProdutos(Pageable pageable);
    @PreAuthorize("hasAnyRole('CLIENT','ADMIN')")
    Page<Produto> getAllProdutosByCategoria(Pageable pageable, String descricaoCategoria);
    @PreAuthorize("hasAnyRole('CLIENT','ADMIN')")
    Produto getProdutoById(Long id);
    @PreAuthorize("hasRole('ADMIN')")
    Produto saveProduto(Produto produto);
    @PreAuthorize("hasRole('ADMIN')")
    void updatePrecoDoProdutoById(Long id, BigDecimal preco);
    @PreAuthorize("hasRole('ADMIN')")
    void deleteProdutoById(Long id);
}
