package com.ClassExchange.uc.manter_cursos;

import lombok.Data;

public record CursoResponse(
        Long cursoId,
        String name,
        String sigla,
        String campusName
) {}
