package com.ClassExchange.uc.manter_disciplinas;

import com.ClassExchange.uc.manter_periodos.PeriodoResponse;
import com.ClassExchange.uc.manter_turmas.TurmaResponse;

public record DisciplinaResponse(
        Long disciplinaId,
        String nome,
        int cargaHoraria,
        PeriodoResponse periodo,
        TurmaResponse turma
) {}
