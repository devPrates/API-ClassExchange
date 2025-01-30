package com.ClassExchange.uc.manter_turmas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/turmas")
public class TurmaController {

    @Autowired
    private TurmaService turmaService;



    // Endpoint para criar uma nova turma
    @PostMapping
    public ResponseEntity<TurmaResponse> createTurma(@RequestBody TurmaRequest turmaRequestDTO) {
        TurmaResponse createdTurma = turmaService.createTurma(turmaRequestDTO);
        return new ResponseEntity<>(createdTurma, HttpStatus.CREATED);
    }

    // Endpoint para atualizar uma turma existente
    @PutMapping("/{turmaId}")
    public ResponseEntity<TurmaResponse> updateTurma(
            @PathVariable Long turmaId,
            @RequestBody TurmaRequest turmaRequestDTO) {
        TurmaResponse updatedTurma = turmaService.updateTurma(turmaId, turmaRequestDTO);
        return new ResponseEntity<>(updatedTurma, HttpStatus.OK);
    }

    // Endpoint para obter detalhes de uma turma
    @GetMapping("/{turmaId}")
    public ResponseEntity<TurmaResponse> getTurma(@PathVariable Long turmaId) {
        TurmaResponse turma = turmaService.getTurmaById(turmaId);
        return new ResponseEntity<>(turma, HttpStatus.OK);
    }

    // Endpoint para listar todas as turmas
    @GetMapping
    public ResponseEntity<List<TurmaResponse>> getAllTurmas() {
        List<TurmaResponse> turmas = turmaService.getAllTurmas();
        return new ResponseEntity<>(turmas, HttpStatus.OK);
    }

    // Método para deletar turma
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // Responde com 204 se a exclusão for bem-sucedida
    public void deleteTurma(@PathVariable Long id) {
        turmaService.deleteTurma(id);
    }
}
