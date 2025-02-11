package com.ClassExchange.uc.manter_cursos;

import com.ClassExchange.uc.manter_campus.Campus;
import com.ClassExchange.uc.manter_campus.CampusRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CursoServiceTest {

    @Mock
    private CursoRepository cursoRepository;

    @Mock
    private CampusRepository campusRepository;

    @Mock
    private CursoMapper cursoMapper;

    @InjectMocks
    private CursoService cursoService;


    @Test
    void deveCriarCursoComSucesso() {
        // Dado
        Long campusId = 1L;
        Campus campus = new Campus("Campus A", "CA", "Endereço A");
        CursoRequest cursoRequest = new CursoRequest("Curso A", "CA", campusId);
        when(campusRepository.findById(campusId)).thenReturn(Optional.of(campus));
        Curso curso = new Curso(1L, "Curso A", "CA", campus, Instant.now(), Instant.now());
        when(cursoMapper.toEntity(cursoRequest)).thenReturn(curso);
        when(cursoRepository.save(curso)).thenReturn(curso);
        when(cursoMapper.toResponseDTO(curso)).thenReturn(new CursoResponse(1L"Curso A", "CA"));

        // Quando
        CursoResponse response = cursoService.criarCurso(cursoRequest);

        // Então
        assertNotNull(response); // Verifica se o retorno não é nulo
        assertEquals("Curso A", response.getName()); // Verifica o nome do curso
        assertEquals("CA", response.getSigla()); // Verifica a sigla do curso
    }

    @Test
    void deveLancarErroQuandoCampusNaoExistir() {
        // Dado
        Long campusId = 1L;
        CursoRequest cursoRequest = new CursoRequest("Curso A", "CA", campusId);
        when(campusRepository.findById(campusId)).thenReturn(Optional.empty());

        // Quando
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            cursoService.criarCurso(cursoRequest);
        });

        // Então
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus()); // Verifica o código de erro
        assertEquals("Campus não encontrado", exception.getReason()); // Verifica a mensagem de erro
    }

    @Test
    void deveAtualizarCursoComSucesso() {
        // Dado
        Long cursoId = 1L;
        Long campusId = 1L;
        CursoRequest cursoRequest = new CursoRequest("Curso Atualizado", "CA", campusId);
        Curso cursoExistente = new Curso("Curso A", "CA", new Campus("Campus A", "CA", "Endereço A"));
        Campus campus = new Campus("Campus Atualizado", "CA", "Endereço Atualizado");
        when(cursoRepository.findById(cursoId)).thenReturn(Optional.of(cursoExistente));
        when(campusRepository.findById(campusId)).thenReturn(Optional.of(campus));
        when(cursoMapper.toEntity(cursoRequest)).thenReturn(new Curso("Curso Atualizado", "CA", campus));
        when(cursoRepository.save(any(Curso.class))).thenReturn(new Curso("Curso Atualizado", "CA", campus));
        when(cursoMapper.toResponseDTO(any(Curso.class))).thenReturn(new CursoResponse("Curso Atualizado", "CA"));

        // Quando
        CursoResponse response = cursoService.atualizarCurso(cursoId, cursoRequest);

        // Então
        assertNotNull(response); // Verifica se o retorno não é nulo
        assertEquals("Curso Atualizado", response.getName()); // Verifica o nome do curso atualizado
        assertEquals("CA", response.getSigla()); // Verifica a sigla do curso
    }

    @Test
    void deveLancarErroQuandoCursoNaoExistirParaAtualizacao() {
        // Dado
        Long cursoId = 1L;
        Long campusId = 1L;
        CursoRequest cursoRequest = new CursoRequest("Curso Atualizado", "CA", campusId);
        when(cursoRepository.findById(cursoId)).thenReturn(Optional.empty());

        // Quando
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            cursoService.atualizarCurso(cursoId, cursoRequest);
        });

        // Então
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus()); // Verifica o código de erro
        assertEquals("Curso não encontrado", exception.getReason()); // Verifica a mensagem de erro
    }

    @Test
    void deveLancarErroQuandoCampusNaoExistirParaAtualizacao() {
        // Dado
        Long cursoId = 1L;
        Long campusId = 1L;
        CursoRequest cursoRequest = new CursoRequest("Curso Atualizado", "CA", campusId);
        Curso cursoExistente = new Curso("Curso A", "CA", new Campus("Campus A", "CA", "Endereço A"));
        when(cursoRepository.findById(cursoId)).thenReturn(Optional.of(cursoExistente));
        when(campusRepository.findById(campusId)).thenReturn(Optional.empty());

        // Quando
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            cursoService.atualizarCurso(cursoId, cursoRequest);
        });

        // Então
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus()); // Verifica o código de erro
        assertEquals("Campus não encontrado", exception.getReason()); // Verifica a mensagem de erro
    }


    @Test
    void deveRetornarTodosOsCursos() {
        // Dado
        Curso curso1 = new Curso("Curso A", "CA", new Campus("Campus A", "CA", "Endereço A"));
        Curso curso2 = new Curso("Curso B", "CB", new Campus("Campus B", "CB", "Endereço B"));
        when(cursoRepository.findAll()).thenReturn(List.of(curso1, curso2));
        when(cursoMapper.toResponseDTO(curso1)).thenReturn(new CursoResponse("Curso A", "CA"));
        when(cursoMapper.toResponseDTO(curso2)).thenReturn(new CursoResponse("Curso B", "CB"));

        // Quando
        List<CursoResponse> response = cursoService.obterTodosCursos();

        // Então
        assertNotNull(response); // Verifica se a lista não é nula
        assertEquals(2, response.size()); // Verifica se dois cursos foram retornados
        assertEquals("Curso A", response.get(0).getName()); // Verifica o nome do primeiro curso
        assertEquals("Curso B", response.get(1).getName()); // Verifica o nome do segundo curso
    }

    @Test
    void deveRetornarCursoPorId() {
        // Dado
        Long cursoId = 1L;
        Curso curso = new Curso("Curso A", "CA", new Campus("Campus A", "CA", "Endereço A"));
        when(cursoRepository.findById(cursoId)).thenReturn(Optional.of(curso));
        when(cursoMapper.toResponseDTO(curso)).thenReturn(new CursoResponse("Curso A", "CA"));

        // Quando
        CursoResponse response = cursoService.obterCursoPorId(cursoId);

        // Então
        assertNotNull(response); // Verifica se o retorno não é nulo
        assertEquals("Curso A", response.getName()); // Verifica o nome do curso
        assertEquals("CA", response.getSigla()); // Verifica a sigla do curso
    }

    @Test
    void deveLancarErroQuandoCursoNaoExistirParaConsulta() {
        // Dado
        Long cursoId = 1L;
        when(cursoRepository.findById(cursoId)).thenReturn(Optional.empty());

        // Quando
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            cursoService.obterCursoPorId(cursoId);
        });

        // Então
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus()); // Verifica o código de erro
        assertEquals("Curso não encontrado", exception.getReason()); // Verifica a mensagem de erro
    }

    @Test
    void deveExcluirCursoComSucesso() {
        // Dado
        Long cursoId = 1L;
        when(cursoRepository.existsById(cursoId)).thenReturn(true);

        // Quando
        cursoService.excluirCurso(cursoId);

        // Então
        verify(cursoRepository).deleteById(cursoId); // Verifica se o curso foi excluído
    }

    @Test
    void deveLancarErroQuandoCursoNaoExistirParaExclusao() {
        // Dado
        Long cursoId = 1L;
        when(cursoRepository.existsById(cursoId)).thenReturn(false);

        // Quando
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            cursoService.excluirCurso(cursoId);
        });

        // Então
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus()); // Verifica o código de erro
        assertEquals("Curso não encontrado", exception.getReason()); // Verifica a mensagem de erro
    }





}