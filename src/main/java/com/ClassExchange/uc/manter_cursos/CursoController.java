package com.ClassExchange.uc.manter_cursos;

import com.ClassExchange.uc.manter_campus.Campus;
import com.ClassExchange.uc.manter_campus.CampusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    private final CursoService cursoService;

    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    // Endpoint para criar um novo curso
    @PostMapping
    public ResponseEntity<CursoResponse> criarCurso(@RequestBody CursoRequest cursoRequestDTO) {
        CursoResponse cursoResponseDTO = cursoService.criarCurso(cursoRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(cursoResponseDTO);
    }

    // Endpoint para atualizar um curso existente
    @PutMapping("/{id}")
    public ResponseEntity<CursoResponse> atualizarCurso(@PathVariable Long id, @RequestBody CursoRequest cursoRequestDTO) {
        CursoResponse cursoResponseDTO = cursoService.atualizarCurso(id, cursoRequestDTO);
        return ResponseEntity.ok(cursoResponseDTO);
    }

    // Endpoint para obter todos os cursos
    @GetMapping
    public ResponseEntity<List<CursoResponse>> obterTodosCursos() {
        List<CursoResponse> cursos = cursoService.obterTodosCursos();
        return ResponseEntity.ok(cursos);
    }

    // Endpoint para obter um curso por id
    @GetMapping("/{id}")
    public ResponseEntity<CursoResponse> obterCursoPorId(@PathVariable Long id) {
        CursoResponse cursoResponseDTO = cursoService.obterCursoPorId(id);
        return ResponseEntity.ok(cursoResponseDTO);
    }

    // Endpoint para excluir um curso por id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirCurso(@PathVariable Long id) {
        cursoService.excluirCurso(id);
        return ResponseEntity.noContent().build();
    }

}