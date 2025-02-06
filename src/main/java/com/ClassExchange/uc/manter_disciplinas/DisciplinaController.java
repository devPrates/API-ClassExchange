package com.ClassExchange.uc.manter_disciplinas;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Controle de Disciplinas")
@RequestMapping("/api/disciplinas")
public class DisciplinaController {

    private final DisciplinaService disciplinaService;

    public DisciplinaController(DisciplinaService disciplinaService) {
        this.disciplinaService = disciplinaService;
    }

    /**
     * Retorna todas as disciplinas cadastradas.
     */
    @Operation(summary = "Listar todas as disciplinas", method = "POST")
    @GetMapping
    public ResponseEntity<List<DisciplinaResponse>> getAllDisciplinas() {
        return ResponseEntity.ok(disciplinaService.getAllDisciplinas());
    }

    /**
     * Retorna uma disciplina pelo ID.
     */
    @Operation(summary = "Listar disciplinas por id", method = "POST")
    @GetMapping("/{disciplinaId}")
    public ResponseEntity<DisciplinaResponse> getDisciplinaById(@PathVariable Long disciplinaId) {
        return ResponseEntity.ok(disciplinaService.getDisciplinaById(disciplinaId));
    }

    /**
     * Cria uma nova disciplina.
     */
    @Operation(summary = "Cadastrar uma nova disciplina no sistema", method = "POST")
    @PostMapping
    public ResponseEntity<DisciplinaResponse> createDisciplina(@RequestBody DisciplinaRequest disciplinaRequestDTO) {
        return ResponseEntity.ok(disciplinaService.createDisciplina(disciplinaRequestDTO));
    }

    /**
     * Atualiza uma disciplina existente.
     */
    @Operation(summary = "Atualizar uma  disciplina no sistema", method = "PUT")
    @PutMapping("/{disciplinaId}")
    public ResponseEntity<DisciplinaResponse> updateDisciplina(
            @PathVariable Long disciplinaId,
            @RequestBody DisciplinaRequest disciplinaRequestDTO) {
        return ResponseEntity.ok(disciplinaService.updateDisciplina(disciplinaId, disciplinaRequestDTO));
    }

    /**
     * Deleta uma disciplina pelo ID.
     */
    @Operation(summary = "Excluir uma disciplina no sistema", method = "DELETE")
    @DeleteMapping("/{disciplinaId}")
    public ResponseEntity<Void> deleteDisciplina(@PathVariable Long disciplinaId) {
        disciplinaService.deleteDisciplina(disciplinaId);
        return ResponseEntity.noContent().build();
    }
}
