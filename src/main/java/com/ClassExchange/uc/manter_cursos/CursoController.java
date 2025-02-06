package com.ClassExchange.uc.manter_cursos;

import com.ClassExchange.uc.manter_campus.Campus;
import com.ClassExchange.uc.manter_campus.CampusRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Tag(name = "Controle de Cursos")
@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    private final CursoService cursoService;

    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    // Endpoint para criar um novo curso
    @Operation(summary = "Cadastrar um novo curso no sistema", method = "POST")
    @PostMapping
    public ResponseEntity<CursoResponse> criarCurso(@RequestBody CursoRequest cursoRequestDTO) {
        CursoResponse cursoResponseDTO = cursoService.criarCurso(cursoRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(cursoResponseDTO);
    }

    // Endpoint para atualizar um curso existente
    @Operation(summary = "Atualizar um curso no sistema", method = "PUT")
    @PutMapping("/{id}")
    public ResponseEntity<CursoResponse> atualizarCurso(@PathVariable Long id, @RequestBody CursoRequest cursoRequestDTO) {
        CursoResponse cursoResponseDTO = cursoService.atualizarCurso(id, cursoRequestDTO);
        return ResponseEntity.ok(cursoResponseDTO);
    }

    // Endpoint para obter todos os cursos
    @Operation(summary = "Listar todas os cursos", method = "POST")
    @GetMapping
    public ResponseEntity<List<CursoResponse>> obterTodosCursos() {
        List<CursoResponse> cursos = cursoService.obterTodosCursos();
        return ResponseEntity.ok(cursos);
    }

    // Endpoint para obter um curso por id
    @Operation(summary = "Listar cursos por id", method = "POST")
    @GetMapping("/{id}")
    public ResponseEntity<CursoResponse> obterCursoPorId(@PathVariable Long id) {
        CursoResponse cursoResponseDTO = cursoService.obterCursoPorId(id);
        return ResponseEntity.ok(cursoResponseDTO);
    }

    // Endpoint para excluir um curso por id
    @Operation(summary = "Excluir um curso no sistema", method = "DELETE")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirCurso(@PathVariable Long id) {
        cursoService.excluirCurso(id);
        return ResponseEntity.noContent().build();
    }

}