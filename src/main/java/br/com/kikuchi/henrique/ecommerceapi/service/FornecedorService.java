package br.com.kikuchi.henrique.ecommerceapi.service;

import br.com.kikuchi.henrique.ecommerceapi.entity.Categoria;
import br.com.kikuchi.henrique.ecommerceapi.entity.Fornecedor;

import java.util.List;

public interface FornecedorService {

    List<Fornecedor> getAllFornecedores();
    Fornecedor getFornecedorById(Long id);
    Fornecedor getFornecedorByDescricao(String descricao);
    Fornecedor saveFornecedor(Fornecedor fornecedor);
    void updateFornecedor(Long id, Fornecedor fornecedor);
    void deleteFornecedorById(Long id);
}
