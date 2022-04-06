package br.com.kikuchi.henrique.ecommerceapi.repository;

import br.com.kikuchi.henrique.ecommerceapi.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
