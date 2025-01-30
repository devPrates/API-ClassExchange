package com.ClassExchange.uc.manter_campus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CampusService {

    @Autowired
    private CampusRepository campusRepository;

    // Método para criar um novo Campus
    public CampusResponse createCampus(CampusRequest requestDTO) {
        // Converte o DTO para a entidade Campus
        Campus campus = CampusMapper.INSTANCE.toEntity(requestDTO);
        campus = campusRepository.save(campus);
        // Retorna o Campus como CampusResponseDTO
        return CampusMapper.INSTANCE.toResponse(campus);
    }

    // Método para buscar todos os Campus
    public List<CampusResponse> getAllCampuses() {
        List<Campus> campuses = campusRepository.findAll();
        return campuses.stream()
                .map(CampusMapper.INSTANCE::toResponse)
                .toList();
    }

    // Método para buscar um Campus por ID
    public Optional<CampusResponse> getCampusById(Long id) {
        Optional<Campus> campus = campusRepository.findById(id);
        return campus.map(CampusMapper.INSTANCE::toResponse);
    }

    // Método para atualizar um Campus
    public Optional<CampusResponse> updateCampus(Long id, CampusRequest requestDTO) {
        Optional<Campus> optionalCampus = campusRepository.findById(id);
        if (optionalCampus.isPresent()) {
            Campus campus = optionalCampus.get();
            campus.setName(requestDTO.getName());
            campus.setSigla(requestDTO.getSigla());
            campus.setEndereco(requestDTO.getEndereco());
            campus = campusRepository.save(campus);
            return Optional.of(CampusMapper.INSTANCE.toResponse(campus));
        }
        return Optional.empty();
    }

    // Método para excluir um Campus
    public boolean deleteCampus(Long id) {
        if (campusRepository.existsById(id)) {
            campusRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

