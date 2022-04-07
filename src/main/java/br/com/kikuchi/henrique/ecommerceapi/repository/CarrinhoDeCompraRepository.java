package br.com.kikuchi.henrique.ecommerceapi.repository;

import br.com.kikuchi.henrique.ecommerceapi.entity.CarrinhoDeCompra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CarrinhoDeCompraRepository extends JpaRepository<CarrinhoDeCompra, Long> {

    @Query("SELECT c FROM tbl_compra c WHERE c.cliente.username = ?1 AND c.status = 0")
    Optional<CarrinhoDeCompra> findCarrinhoDeCompraEmAbertoByClientUsername(String username);

    List<CarrinhoDeCompra> findAllByCliente_Username(String username);

    Optional<CarrinhoDeCompra> findCarrinhoDeCompraByIdAndCliente_Username(Long id, String username);

}
