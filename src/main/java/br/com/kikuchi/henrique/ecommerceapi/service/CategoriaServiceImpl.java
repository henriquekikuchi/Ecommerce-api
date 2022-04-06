package br.com.kikuchi.henrique.ecommerceapi.service;

import br.com.kikuchi.henrique.ecommerceapi.entity.Categoria;
import br.com.kikuchi.henrique.ecommerceapi.exception.CategoriaNotFound;
import br.com.kikuchi.henrique.ecommerceapi.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository categoriaRepository;

    @Override
    public List<Categoria> getAllCategorias() {
        return categoriaRepository.findAll();
    }

    @Override
    public Categoria getCategoriaById(Long id) {
        return categoriaRepository.findById(id).orElseThrow(CategoriaNotFound::new);
    }

    @Override
    public Categoria getCategoriaByDescricao(String descricao) {
        return categoriaRepository.findByDescricao(descricao).orElseThrow(CategoriaNotFound::new);
    }

    @Override
    public Categoria saveCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    @Override
    public void updateCategoria(Long id, Categoria categoria) {
        Categoria cat = getCategoriaById(id);
        cat.setDescricao(categoria.getDescricao());
        categoriaRepository.saveAndFlush(cat);
    }

    @Override
    public void deleteCategoriaById(Long id) {
        getCategoriaById(id);
        categoriaRepository.deleteById(id);
    }
}
