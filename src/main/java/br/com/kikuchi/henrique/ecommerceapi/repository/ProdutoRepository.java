package br.com.kikuchi.henrique.ecommerceapi.repository;

import br.com.kikuchi.henrique.ecommerceapi.entity.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    Page<Produto> findAllByCategoria_Descricao(Pageable pageable, String descricao);

    @Query("Select p from tbl_produto p where p.categoria.id in (?1)")
    Page<Produto> findAllByCategoria_IdIn(Pageable pageable, long[] idCategorias);
}
