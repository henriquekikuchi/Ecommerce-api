package br.com.kikuchi.henrique.ecommerceapi.repository;

import br.com.kikuchi.henrique.ecommerceapi.entity.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {

}
