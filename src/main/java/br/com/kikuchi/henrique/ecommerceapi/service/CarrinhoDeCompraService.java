package br.com.kikuchi.henrique.ecommerceapi.service;

import br.com.kikuchi.henrique.ecommerceapi.entity.CarrinhoDeCompra;
import br.com.kikuchi.henrique.ecommerceapi.entity.Produto;

import java.util.List;
import java.util.UUID;

public interface CarrinhoDeCompraService {

    List<CarrinhoDeCompra> getAllCarrinhoDeCompra();
    CarrinhoDeCompra getCarrinhoDeCompraById(Long id);
    CarrinhoDeCompra saveCarrinhoDeCompra(String username);
    boolean hasUserCarrinhoDeCompraRealizado(String username);
    void encerraCarrinhoDeCompra(Long id);
    void entregueCarrinhoDeCompra(Long id);
    void addProdutoAoCarrinhoDeCompra(Long id, Produto produto);
    void addProdutoAoCarrinhoDeCompra(Long id, Produto... produtos);
    void removeProdutoDoCarrinhoDeCompra(Long id, Produto produto);
}
