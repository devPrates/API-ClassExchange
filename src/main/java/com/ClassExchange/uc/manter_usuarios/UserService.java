package com.ClassExchange.uc.manter_usuarios;

import com.ClassExchange.uc.manter_roles.Role;
import com.ClassExchange.uc.manter_roles.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Transactional
    public void createNewUser(CreateUserMapper dto) {
        var basicRole = roleRepository.findByName(Role.Values.BASIC.name());

        if (basicRole == null) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Role BASIC Não encontrada");
        }

        var userFromDb = userRepository.findByEmail(dto.email());
        if (userFromDb.isPresent()) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Username already exists");
        }

        var user = new User();
        user.setName(dto.name());
        user.setEmail(dto.email());
        user.setPassword(passwordEncoder.encode(dto.password()));
        user.setSiape(dto.siape());
        user.setCelular(dto.celular());
        user.setRoles(Set.of(basicRole));

        userRepository.save(user);
    }

    @Transactional
    public void updateUser(UUID userId, UpdateUserMapper dto) {
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));

        var basicRole = roleRepository.findByName(Role.Values.BASIC.name());
        if (basicRole == null) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Role BASIC não encontrada");
        }

        // Atualizar os campos básicos do usuário
        user.setName(dto.name());
        user.setEmail(dto.email());
        user.setSiape(dto.siape());
        user.setCelular(dto.celular());

        // Sempre redefinir a role como BASIC
        user.setRoles(new HashSet<>(Set.of(basicRole))); // Always reset to BASIC role

        userRepository.save(user); // Salvar alterações
    }

    @Transactional
    public void deleteUserById(UUID userId) {
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));

        // Remover as associações entre o usuário e as roles
        user.getRoles().clear();
        userRepository.save(user); // Salva o estado atualizado do usuário sem associações

        // Excluir o usuário
        userRepository.delete(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
