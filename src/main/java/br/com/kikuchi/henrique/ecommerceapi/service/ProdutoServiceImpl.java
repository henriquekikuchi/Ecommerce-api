package br.com.kikuchi.henrique.ecommerceapi.service;

import br.com.kikuchi.henrique.ecommerceapi.entity.Produto;
import br.com.kikuchi.henrique.ecommerceapi.exception.ProdutoNotFound;
import br.com.kikuchi.henrique.ecommerceapi.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Service
public class ProdutoServiceImpl implements ProdutoService {

    private final ProdutoRepository produtoRepository;

    @Override
    public Page<Produto> getAllProdutos(Pageable pageable) {
        return produtoRepository.findAll(pageable);
    }

    @Override
    public Page<Produto> getAllProdutosByCategoria(Pageable pageable, String descricaoCategoria) {
        return produtoRepository.findAllByCategoria_Descricao(pageable, descricaoCategoria);
    }

    @Override
    public Produto getProdutoById(Long id) {
        return produtoRepository.findById(id).orElseThrow(() -> new ProdutoNotFound());
    }

    @Override
    public Produto saveProduto(Produto produto) {
        return produtoRepository.save(produto);
    }

    @Override
    public void updatePrecoDoProdutoById(Long id, BigDecimal preco) {
        Produto produto = getProdutoById(id);
        produto.setPreco(preco);
        produtoRepository.saveAndFlush(produto);
    }

    @Override
    public void deleteProdutoById(Long id) {
        getProdutoById(id);
        produtoRepository.deleteById(id);
    }
}
