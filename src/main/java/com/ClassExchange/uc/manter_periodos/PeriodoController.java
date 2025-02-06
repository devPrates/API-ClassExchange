package com.ClassExchange.uc.manter_periodos;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Controle de Periodos")
@RequestMapping("/api/periodos")
public class PeriodoController {
    private final PeriodoService periodoService;

    public PeriodoController(PeriodoService periodoService) {
        this.periodoService = periodoService;
    }

    @Operation(summary = "Cadastrar um novo Periodo no sistema", method = "POST")
    @PostMapping
    public ResponseEntity<PeriodoResponse> createPeriodo(@RequestBody PeriodoRequest request) {
        return ResponseEntity.ok(periodoService.createPeriodo(request));
    }

    @Operation(summary = "Listar todas os periodos", method = "POST")
    @GetMapping
    public ResponseEntity<List<PeriodoResponse>> getAllPeriodos() {
        return ResponseEntity.ok(periodoService.getAllPeriodos());
    }

    @Operation(summary = "Listar periodos por id", method = "POST")
    @GetMapping("/{id}")
    public ResponseEntity<PeriodoResponse> getPeriodoById(@PathVariable Long id) {
        return ResponseEntity.ok(periodoService.getPeriodoById(id));
    }

    @Operation(summary = "Atualizar um periodo no sistema", method = "PUT")
    @PutMapping("/{id}")
    public ResponseEntity<PeriodoResponse> updatePeriodo(@PathVariable Long id, @RequestBody PeriodoRequest request) {
        return ResponseEntity.ok(periodoService.updatePeriodo(id, request));
    }

    @Operation(summary = "Excluir um periodo no sistema", method = "DELETE")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePeriodo(@PathVariable Long id) {
        periodoService.deletePeriodo(id);
        return ResponseEntity.noContent().build();
    }
}
