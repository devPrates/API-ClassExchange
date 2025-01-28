package com.ClassExchange.uc.manter_usuarios;

import com.ClassExchange.uc.manter_roles.Role;

import java.util.Set;

public record UpdateUserMapper(String name,
                               String email,
                               String siape,
                               String celular,
                               Set<Role> roles // IDs das roles associadas ao usuário
){}
