package com.ClassExchange.uc.manter_campus;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CampusMapper {

    CampusMapper INSTANCE = Mappers.getMapper(CampusMapper.class);

    // Método de mapeamento da entidade para o DTO de resposta
    CampusResponse toResponse(Campus campus);


    // Método de mapeamento do DTO de requisição para a entidade
    Campus toEntity(CampusRequest dto);
}
