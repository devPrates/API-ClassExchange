package com.ClassExchange.uc.manter_cursos;

public record CursoRequest(
        String name,
        String sigla,
        Long campusId
) {}
