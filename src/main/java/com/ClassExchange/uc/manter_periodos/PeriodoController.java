package com.ClassExchange.uc.manter_periodos;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/periodos")
public class PeriodoController {
    private final PeriodoService periodoService;

    public PeriodoController(PeriodoService periodoService) {
        this.periodoService = periodoService;
    }

    @PostMapping
    public ResponseEntity<PeriodoResponse> createPeriodo(@RequestBody PeriodoRequest request) {
        return ResponseEntity.ok(periodoService.createPeriodo(request));
    }

    @GetMapping
    public ResponseEntity<List<PeriodoResponse>> getAllPeriodos() {
        return ResponseEntity.ok(periodoService.getAllPeriodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PeriodoResponse> getPeriodoById(@PathVariable Long id) {
        return ResponseEntity.ok(periodoService.getPeriodoById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PeriodoResponse> updatePeriodo(@PathVariable Long id, @RequestBody PeriodoRequest request) {
        return ResponseEntity.ok(periodoService.updatePeriodo(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePeriodo(@PathVariable Long id) {
        periodoService.deletePeriodo(id);
        return ResponseEntity.noContent().build();
    }
}
