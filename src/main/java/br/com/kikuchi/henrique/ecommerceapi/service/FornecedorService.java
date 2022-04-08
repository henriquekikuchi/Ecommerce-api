package br.com.kikuchi.henrique.ecommerceapi.service;

import br.com.kikuchi.henrique.ecommerceapi.entity.Categoria;
import br.com.kikuchi.henrique.ecommerceapi.entity.Fornecedor;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@PreAuthorize("hasRole('ADMIN')")
public interface FornecedorService {

    List<Fornecedor> getAllFornecedores();
    Fornecedor getFornecedorById(Long id);
    Fornecedor getFornecedorByDescricao(String descricao);
    Fornecedor saveFornecedor(Fornecedor fornecedor);
    void updateFornecedor(Long id, Fornecedor fornecedor);
    void deleteFornecedorById(Long id);
}
