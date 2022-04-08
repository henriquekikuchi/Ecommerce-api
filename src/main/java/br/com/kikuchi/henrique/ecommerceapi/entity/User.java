package br.com.kikuchi.henrique.ecommerceapi.entity;

import br.com.kikuchi.henrique.ecommerceapi.security.ApplicationUserRole;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "tbl_user")
public class User {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private UUID uuid;
    @Column(unique = true)
    private String username;
    private String password;
    private ApplicationUserRole role;
}
