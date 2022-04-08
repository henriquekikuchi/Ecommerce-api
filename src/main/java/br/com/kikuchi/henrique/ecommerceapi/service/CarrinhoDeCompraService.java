package br.com.kikuchi.henrique.ecommerceapi.service;

import br.com.kikuchi.henrique.ecommerceapi.dto.CarrinhoDeCompraResponseDTO;
import br.com.kikuchi.henrique.ecommerceapi.entity.CarrinhoDeCompra;
import br.com.kikuchi.henrique.ecommerceapi.entity.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

public interface CarrinhoDeCompraService {

    @PreAuthorize("hasAnyRole('CLIENT','ADMIN')")
    Page<CarrinhoDeCompraResponseDTO> getAllCarrinhoDeCompra(Pageable pageable);
    @PreAuthorize("hasAnyRole('CLIENT','ADMIN')")
    CarrinhoDeCompra getCarrinhoDeCompraById(Long id);
    @PreAuthorize("hasAnyRole('CLIENT','ADMIN')")
    CarrinhoDeCompra saveCarrinhoDeCompra(List<Long> idsProdutos);
    @PreAuthorize("hasAnyRole('CLIENT','ADMIN')")
    void realizarCarrinhoDeCompra(Long id);
    @PreAuthorize("hasRole('ADMIN')")
    void encerraCarrinhoDeCompra(Long id);
    @PreAuthorize("hasRole('ADMIN')")
    void entregueCarrinhoDeCompra(Long id);
    @PreAuthorize("hasRole('ADMIN')")
    void cancelarCarrinhoDeCompra(Long id);
    void addProdutoAoCarrinhoDeCompra(Long id, Produto produto);
    void addProdutoAoCarrinhoDeCompra(Long id, Produto... produtos);
    void removeProdutoDoCarrinhoDeCompra(Long id, Produto produto);
    void removeCarrinhoDeCompra(Long id);
}
