package br.com.kikuchi.henrique.ecommerceapi.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import java.time.LocalDateTime;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name="tbl_categoria")
public class Categoria {

    @Id @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(unique = true)
    private String descricao;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime created_at;

    @UpdateTimestamp
    private LocalDateTime updated_at;
}
