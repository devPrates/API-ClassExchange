package com.ClassExchange.uc.manter_disciplinas;

public record DisciplinaRequest(
        String nome,
        int cargaHoraria,
        Long periodoId,
        Long turmaId
) {}
