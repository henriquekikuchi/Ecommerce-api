package br.com.kikuchi.henrique.ecommerceapi.entity;

import lombok.*;

import javax.persistence.*;

import java.math.BigDecimal;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name="tbl_produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @Column(unique = true)
    private String nome;
    private BigDecimal preco;
    @ManyToOne(optional = false, targetEntity = Fornecedor.class)
    private Fornecedor fornecedor;
    @ManyToOne(optional = false, targetEntity = Categoria.class, cascade = {CascadeType.ALL})
    private Categoria categoria;
}
