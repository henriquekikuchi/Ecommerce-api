package br.com.kikuchi.henrique.ecommerceapi.service;

import br.com.kikuchi.henrique.ecommerceapi.entity.CarrinhoDeCompra;
import br.com.kikuchi.henrique.ecommerceapi.entity.Produto;
import java.util.List;

public interface CarrinhoDeCompraService {

    List<CarrinhoDeCompra> getAllCarrinhoDeCompra();
    CarrinhoDeCompra getCarrinhoDeCompraById(Long id);
    CarrinhoDeCompra saveCarrinhoDeCompra(List<Long> idsProdutos);
    boolean hasUserCarrinhoDeCompraEmAberto(String username);
    void realizarCarrinhoDeCompra(Long id);
    void encerraCarrinhoDeCompra(Long id);
    void entregueCarrinhoDeCompra(Long id);
    void cancelarCarrinhoDeCompra(Long id);
    void addProdutoAoCarrinhoDeCompra(Long id, Produto produto);
    void addProdutoAoCarrinhoDeCompra(Long id, Produto... produtos);
    void removeProdutoDoCarrinhoDeCompra(Long id, Produto produto);
    void removeCarrinhoDeCompra(Long id);
}
