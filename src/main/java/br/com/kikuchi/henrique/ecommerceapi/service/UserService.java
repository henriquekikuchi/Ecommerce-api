package br.com.kikuchi.henrique.ecommerceapi.service;


import br.com.kikuchi.henrique.ecommerceapi.entity.User;
import br.com.kikuchi.henrique.ecommerceapi.security.ApplicationUserRole;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;


public interface UserService extends UserDetailsService {

    @PreAuthorize("hasRole('ADMIN')")
    List<User> getAllUsers();
    @PreAuthorize("hasRole('ADMIN')")
    User getUserById(Long id);
    @PreAuthorize("hasRole('ADMIN')")
    User saveUser(User user);
    @PreAuthorize("hasRole('ADMIN')")
    void grantRoleById(Long id, ApplicationUserRole role);
}
