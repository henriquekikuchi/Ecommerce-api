package br.com.kikuchi.henrique.ecommerceapi.service;

import br.com.kikuchi.henrique.ecommerceapi.entity.User;
import br.com.kikuchi.henrique.ecommerceapi.repository.UserRepository;
import br.com.kikuchi.henrique.ecommerceapi.security.ApplicationUserRole;
import br.com.kikuchi.henrique.ecommerceapi.security.UserSecurity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("Usuario e/ou senha não encontrado!"));
        return new UserSecurity(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    @Override
    public User saveUser(User user) {
        user.setUuid(UUID.randomUUID());
        return userRepository.save(user);
    }

    @Override
    public void grantRoleById(Long id, ApplicationUserRole role) {
        User user = getUserById(id);
        user.setRole(role);
        saveUser(user);
    }

}
