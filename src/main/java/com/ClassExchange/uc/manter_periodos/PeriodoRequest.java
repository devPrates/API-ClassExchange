package com.ClassExchange.uc.manter_periodos;

import java.time.Instant;

public record PeriodoRequest(
        String nome,
        TipoPeriodo tipoPeriodo,
        int numero,
        int ano,
        Instant inicio,
        Instant fim,
        Long turmaId
) {}
