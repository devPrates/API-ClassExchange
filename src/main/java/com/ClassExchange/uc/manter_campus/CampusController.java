package com.ClassExchange.uc.manter_campus;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "Controle de Campus")
@RestController
@RequestMapping("/api/campus")
public class CampusController {

    @Autowired
    private CampusService campusService;

    // Endpoint para criar um novo Campus
    @Operation(summary = "Cadastrar um novo campus no sistema", method = "POST")
    @PostMapping
    public ResponseEntity<CampusResponse> createCampus(@RequestBody CampusRequest requestDTO) {
        CampusResponse responseDTO = campusService.createCampus(requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    // Endpoint para obter todos os Campuses
    @Operation(summary = "Listar todas os campus", method = "POST")
    @GetMapping
    public ResponseEntity<List<CampusResponse>> getAllCampuses() {
        List<CampusResponse> responseDTOs = campusService.getAllCampuses();
        return ResponseEntity.ok(responseDTOs);
    }

    // Endpoint para obter um Campus por ID
    @Operation(summary = "Listar campus por id", method = "POST")
    @GetMapping("/{id}")
    public ResponseEntity<CampusResponse> getCampusById(@PathVariable Long id) {
        Optional<CampusResponse> responseDTO = campusService.getCampusById(id);
        return responseDTO.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Endpoint para atualizar um Campus por ID
    @Operation(summary = "Atualizar um campus no sistema", method = "PUT")
    @PutMapping("/{id}")
    public ResponseEntity<CampusResponse> updateCampus(@PathVariable Long id, @RequestBody CampusRequest requestDTO) {
        Optional<CampusResponse> responseDTO = campusService.updateCampus(id, requestDTO);
        return responseDTO.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Endpoint para excluir um Campus por ID
    @Operation(summary = "Excluir um campus no sistema", method = "DELETE")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCampus(@PathVariable Long id) {
        boolean deleted = campusService.deleteCampus(id);
        return deleted ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}

