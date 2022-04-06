package br.com.kikuchi.henrique.ecommerceapi.service;


import br.com.kikuchi.henrique.ecommerceapi.entity.Fornecedor;
import br.com.kikuchi.henrique.ecommerceapi.entity.User;
import br.com.kikuchi.henrique.ecommerceapi.security.ApplicationUserRole;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    List<User> getAllUsers();
    User getUserById(Long id);
    User saveUser(User user);
    void grantRoleById(Long id, ApplicationUserRole role);
}
