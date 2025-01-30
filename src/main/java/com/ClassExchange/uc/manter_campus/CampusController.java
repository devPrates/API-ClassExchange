package com.ClassExchange.uc.manter_campus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/campus")
public class CampusController {

    @Autowired
    private CampusService campusService;

    // Endpoint para criar um novo Campus
    @PostMapping
    public ResponseEntity<CampusResponse> createCampus(@RequestBody CampusRequest requestDTO) {
        CampusResponse responseDTO = campusService.createCampus(requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    // Endpoint para obter todos os Campuses
    @GetMapping
    public ResponseEntity<List<CampusResponse>> getAllCampuses() {
        List<CampusResponse> responseDTOs = campusService.getAllCampuses();
        return ResponseEntity.ok(responseDTOs);
    }

    // Endpoint para obter um Campus por ID
    @GetMapping("/{id}")
    public ResponseEntity<CampusResponse> getCampusById(@PathVariable Long id) {
        Optional<CampusResponse> responseDTO = campusService.getCampusById(id);
        return responseDTO.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Endpoint para atualizar um Campus por ID
    @PutMapping("/{id}")
    public ResponseEntity<CampusResponse> updateCampus(@PathVariable Long id, @RequestBody CampusRequest requestDTO) {
        Optional<CampusResponse> responseDTO = campusService.updateCampus(id, requestDTO);
        return responseDTO.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Endpoint para excluir um Campus por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCampus(@PathVariable Long id) {
        boolean deleted = campusService.deleteCampus(id);
        return deleted ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}

