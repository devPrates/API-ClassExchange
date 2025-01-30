package com.ClassExchange.uc.manter_turmas;

public record TurmaResponse(
        Long turmaId,
        String nome,
        int ano,
        Long cursoId
) {}
