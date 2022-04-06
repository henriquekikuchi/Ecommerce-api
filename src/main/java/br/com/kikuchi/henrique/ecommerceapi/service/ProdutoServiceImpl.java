package br.com.kikuchi.henrique.ecommerceapi.service;

import br.com.kikuchi.henrique.ecommerceapi.entity.Produto;
import br.com.kikuchi.henrique.ecommerceapi.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class ProdutoServiceImpl implements ProdutoService {

    private final ProdutoRepository produtoRepository;

    @Override
    public Page<Produto> getAllProdutos(Pageable pageable) {
        return null;
    }

    @Override
    public Produto getProdutoById(Long id) {
        return null;
    }

    @Override
    public Produto saveProduto(Produto produto) {
        return null;
    }

    @Override
    public void updatePrecoDoProdutoById(Long id, BigDecimal preco) {

    }

    @Override
    public void deleteProdutoById(Long id) {

    }
}
