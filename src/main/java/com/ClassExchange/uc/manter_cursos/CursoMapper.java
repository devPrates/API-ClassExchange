package com.ClassExchange.uc.manter_cursos;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface CursoMapper {

    CursoMapper INSTANCE = Mappers.getMapper(CursoMapper.class);

    // Mapeia CursoRequestDTO para Curso (ao criar ou atualizar um curso)
    @Mapping(source = "campusId", target = "campus.campusId") // Mapeia campusId para campus.id
    Curso toEntity(CursoRequest cursoRequestDTO);

    // Mapeia Curso para CursoResponseDTO
    @Mapping(source = "campus.name", target = "campusName") // Mapeia campus.name para campusName no DTO
    CursoResponse toResponseDTO(Curso curso);
}