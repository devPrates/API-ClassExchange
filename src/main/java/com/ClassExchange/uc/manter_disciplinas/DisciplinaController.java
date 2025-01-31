package com.ClassExchange.uc.manter_disciplinas;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/disciplinas")
public class DisciplinaController {

    private final DisciplinaService disciplinaService;

    public DisciplinaController(DisciplinaService disciplinaService) {
        this.disciplinaService = disciplinaService;
    }

    /**
     * Retorna todas as disciplinas cadastradas.
     */
    @GetMapping
    public ResponseEntity<List<DisciplinaResponse>> getAllDisciplinas() {
        return ResponseEntity.ok(disciplinaService.getAllDisciplinas());
    }

    /**
     * Retorna uma disciplina pelo ID.
     */
    @GetMapping("/{disciplinaId}")
    public ResponseEntity<DisciplinaResponse> getDisciplinaById(@PathVariable Long disciplinaId) {
        return ResponseEntity.ok(disciplinaService.getDisciplinaById(disciplinaId));
    }

    /**
     * Cria uma nova disciplina.
     */
    @PostMapping
    public ResponseEntity<DisciplinaResponse> createDisciplina(@RequestBody DisciplinaRequest disciplinaRequestDTO) {
        return ResponseEntity.ok(disciplinaService.createDisciplina(disciplinaRequestDTO));
    }

    /**
     * Atualiza uma disciplina existente.
     */
    @PutMapping("/{disciplinaId}")
    public ResponseEntity<DisciplinaResponse> updateDisciplina(
            @PathVariable Long disciplinaId,
            @RequestBody DisciplinaRequest disciplinaRequestDTO) {
        return ResponseEntity.ok(disciplinaService.updateDisciplina(disciplinaId, disciplinaRequestDTO));
    }

    /**
     * Deleta uma disciplina pelo ID.
     */
    @DeleteMapping("/{disciplinaId}")
    public ResponseEntity<Void> deleteDisciplina(@PathVariable Long disciplinaId) {
        disciplinaService.deleteDisciplina(disciplinaId);
        return ResponseEntity.noContent().build();
    }
}
