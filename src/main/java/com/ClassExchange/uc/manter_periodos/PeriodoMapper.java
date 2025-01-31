package com.ClassExchange.uc.manter_periodos;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PeriodoMapper {
    PeriodoMapper INSTANCE = Mappers.getMapper(PeriodoMapper.class);

    @Mapping(source = "turmaId", target = "turma.turmaId")
    Periodo toEntity(PeriodoRequest request);

    @Mapping(source = "turma.turmaId", target = "turmaId")
    PeriodoResponse toResponse(Periodo periodo);
}

