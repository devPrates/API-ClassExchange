package com.ClassExchange.uc.manter_usuarios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@EnableMethodSecurity
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<String> newUser(@RequestBody CreateUserMapper dto) {
        userService.createNewUser(dto);
        return ResponseEntity.ok("usuário criado com sucesso");
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<String> deleteUser(@PathVariable("id") UUID userId) {
        userService.deleteUserById(userId);
        return ResponseEntity.ok("usuário excluído com sucesso");
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<String> updateUser(@PathVariable("id") UUID userId, @RequestBody UpdateUserMapper dto) {
        userService.updateUser(userId, dto);
        return ResponseEntity.ok("Usuário atualizado com sucesso");
    }

    @GetMapping
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<List<User>> listUsers() {
        var users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
}
