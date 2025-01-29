package com.ClassExchange.uc.manter_campus;

import org.springframework.beans.BeanUtils;

public class CampusMapper {
    public static Campus toEntity(CampusRequest requestDTO) {
        Campus campus = new Campus();
        BeanUtils.copyProperties(requestDTO, campus);
        return campus;
    }

    public static CampusResponse toResponseDTO(Campus campus) {
        CampusResponse responseDTO = new CampusResponse(campus.getCampusId(), campus.getName(), campus.getSigla(), campus.getEndereco());
        return responseDTO;
    }
}
