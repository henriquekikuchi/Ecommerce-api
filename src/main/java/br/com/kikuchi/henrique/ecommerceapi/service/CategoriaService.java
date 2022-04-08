package br.com.kikuchi.henrique.ecommerceapi.service;

import br.com.kikuchi.henrique.ecommerceapi.entity.Categoria;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

public interface CategoriaService {

    @PreAuthorize("hasAnyRole('CLIENT','ADMIN')")
    List<Categoria> getAllCategorias();
    @PreAuthorize("hasAnyRole('CLIENT','ADMIN')")
    Categoria getCategoriaById(Long id);
    @PreAuthorize("hasAnyRole('CLIENT','ADMIN')")
    Categoria getCategoriaByDescricao(String descricao);
    @PreAuthorize("hasRole('ADMIN')")
    Categoria saveCategoria(Categoria categoria);
    @PreAuthorize("hasRole('ADMIN')")
    void updateCategoria(Long id, Categoria categoria);
    @PreAuthorize("hasRole('ADMIN')")
    void deleteCategoriaById(Long id);
}
