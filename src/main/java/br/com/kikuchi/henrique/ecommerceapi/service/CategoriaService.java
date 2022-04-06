package br.com.kikuchi.henrique.ecommerceapi.service;

import br.com.kikuchi.henrique.ecommerceapi.entity.Categoria;
import br.com.kikuchi.henrique.ecommerceapi.entity.Fornecedor;

import java.util.List;

public interface CategoriaService {

    List<Categoria> getAllCategorias();
    Categoria getCategoriaById(Long id);
    Categoria saveCategoria(Categoria categoria);
    void updateCategoria(Long id, Categoria categoria);
    void deleteCategoriaById(Long id);
}
