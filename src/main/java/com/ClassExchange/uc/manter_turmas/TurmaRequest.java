package com.ClassExchange.uc.manter_turmas;

public record TurmaRequest(
        String nome,
        int ano,
        Long cursoId
) {}
