package br.com.kikuchi.henrique.ecommerceapi.repository;

import br.com.kikuchi.henrique.ecommerceapi.entity.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    Page<Produto> findAllByCategoria_Descricao(Pageable pageable, String descricao);
}
