package br.com.kikuchi.henrique.ecommerceapi.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name="tbl_compra")
public class CarrinhoDeCompra {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    @JoinTable(name="tbl_compra_produto",
            joinColumns=
            @JoinColumn(name="compra_id", referencedColumnName="id"),
            inverseJoinColumns=
            @JoinColumn(name="produto_id", referencedColumnName="id")
    )
    private List<Produto> produtos;

    @ManyToOne(targetEntity = User.class)
    private User cliente;

    private CarrinhoDeCompraStatus status;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime created_at;

    @UpdateTimestamp
    private LocalDateTime updated_at;
}
