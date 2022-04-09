package br.com.kikuchi.henrique.ecommerceapi.controller;

import br.com.kikuchi.henrique.ecommerceapi.entity.User;
import br.com.kikuchi.henrique.ecommerceapi.security.ApplicationUserRole;
import br.com.kikuchi.henrique.ecommerceapi.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserManagementController {

    private final UserServiceImpl userService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

    @PostMapping(value = "/create")
    public ResponseEntity<?> saveUser(@RequestBody User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(ApplicationUserRole.CLIENT);
        return ResponseEntity.ok().body(userService.saveUser(user).getUuid());
    }

    @PutMapping(value = "/{userId}/grant-permission")
    public ResponseEntity<?>  grantRoleById(@PathVariable("userId") Long userId, @RequestParam String roleName){
        ApplicationUserRole role = ApplicationUserRole.valueOf(roleName);
        userService.grantRoleById(userId,role);
        return ResponseEntity.noContent().build();
    }
}
