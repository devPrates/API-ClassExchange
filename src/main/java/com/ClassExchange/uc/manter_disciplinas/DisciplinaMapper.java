package com.ClassExchange.uc.manter_disciplinas;

import com.ClassExchange.uc.manter_periodos.Periodo;
import com.ClassExchange.uc.manter_periodos.PeriodoMapper;
import com.ClassExchange.uc.manter_turmas.TurmaMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring", uses = {PeriodoMapper.class, TurmaMapper.class})
public abstract class DisciplinaMapper {

    public static final DisciplinaMapper INSTANCE = Mappers.getMapper(DisciplinaMapper.class);

    @Mapping(target = "disciplinaId", source = "disciplina.disciplinaId")
    @Mapping(target = "periodo", source = "disciplina.periodo")
    @Mapping(target = "turma", source = "disciplina.turma")
    public abstract DisciplinaResponse toResponse(Disciplina disciplina);

    @Mapping(target = "disciplinaId", ignore = true)
    @Mapping(target = "periodo.periodoId", source = "disciplinaRequestDTO.periodoId")
    @Mapping(target = "turma.turmaId", source = "disciplinaRequestDTO.turmaId")
    public abstract Disciplina toEntity(DisciplinaRequest disciplinaRequestDTO);
}
