package com.ClassExchange.uc.manter_usuarios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@EnableMethodSecurity
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<?> newUser(@RequestBody CreateUserMapper dto) {
        userService.createNewUser(dto);
        return ResponseEntity.ok(Map.of("message", "Usuário criado com sucesso"));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<?> deleteUser(@PathVariable("id") UUID userId) {
        userService.deleteUserById(userId);
        return ResponseEntity.ok(Map.of("message","Usuário excluido com sucesso"));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<?> updateUser(@PathVariable("id") UUID userId, @RequestBody UpdateUserMapper dto) {
        userService.updateUser(userId, dto);
        return ResponseEntity.ok(Map.of("message","Usuário atualizado com sucesso"));
    }

    @GetMapping
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<List<User>> listUsers() {
        var users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
}
