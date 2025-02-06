package com.ClassExchange.uc.manter_turmas;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Controle de Turmas")
@RequestMapping("/api/turmas")
public class TurmaController {

    @Autowired
    private TurmaService turmaService;



    // Endpoint para criar uma nova turma
    @Operation(summary = "Cadastrar uma nova turma no sistema", method = "POST")
    @PostMapping
    public ResponseEntity<TurmaResponse> createTurma(@RequestBody TurmaRequest turmaRequestDTO) {
        TurmaResponse createdTurma = turmaService.createTurma(turmaRequestDTO);
        return new ResponseEntity<>(createdTurma, HttpStatus.CREATED);
    }

    // Endpoint para atualizar uma turma existente
    @Operation(summary = "Atualizar uma  turma no sistema", method = "PUT")
    @PutMapping("/{turmaId}")
    public ResponseEntity<TurmaResponse> updateTurma(
            @PathVariable Long turmaId,
            @RequestBody TurmaRequest turmaRequestDTO) {
        TurmaResponse updatedTurma = turmaService.updateTurma(turmaId, turmaRequestDTO);
        return new ResponseEntity<>(updatedTurma, HttpStatus.OK);
    }

    // Endpoint para obter detalhes de uma turma
    @Operation(summary = "Listar turmas por id", method = "POST")
    @GetMapping("/{turmaId}")
    public ResponseEntity<TurmaResponse> getTurma(@PathVariable Long turmaId) {
        TurmaResponse turma = turmaService.getTurmaById(turmaId);
        return new ResponseEntity<>(turma, HttpStatus.OK);
    }

    // Endpoint para listar todas as turmas
    @Operation(summary = "Listar todas as turmas", method = "POST")
    @GetMapping
    public ResponseEntity<List<TurmaResponse>> getAllTurmas() {
        List<TurmaResponse> turmas = turmaService.getAllTurmas();
        return new ResponseEntity<>(turmas, HttpStatus.OK);
    }

    // Método para deletar turma
    @Operation(summary = "Excluir uma turma no sistema", method = "DELETE")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // Responde com 204 se a exclusão for bem-sucedida
    public void deleteTurma(@PathVariable Long id) {
        turmaService.deleteTurma(id);
    }
}
