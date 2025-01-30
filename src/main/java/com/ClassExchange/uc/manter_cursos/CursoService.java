package com.ClassExchange.uc.manter_cursos;

import com.ClassExchange.uc.manter_campus.Campus;
import com.ClassExchange.uc.manter_campus.CampusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class CursoService {

    private final CursoRepository cursoRepository;
    private final CampusRepository campusRepository; // Para verificar o Campus ao associar ao Curso
    private final CursoMapper cursoMapper;

    @Autowired
    public CursoService(CursoRepository cursoRepository, CampusRepository campusRepository, CursoMapper cursoMapper) {
        this.cursoRepository = cursoRepository;
        this.campusRepository = campusRepository;
        this.cursoMapper = cursoMapper;
    }

    // Cria um novo curso
    public CursoResponse criarCurso(CursoRequest cursoRequestDTO) {
        // Verifica se o campusId existe
        Optional<Campus> campus = campusRepository.findById(cursoRequestDTO.campusId());
        if (campus.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Campus não encontrado");
        }

        // Converte o DTO para a entidade
        Curso curso = cursoMapper.toEntity(cursoRequestDTO);
        curso.setCreateDate();  // Define a data de criação
        curso.setUpdateDate();  // Define a data de atualização
        curso.setCampus(campus.get()); // Associa o Campus ao Curso

        // Salva o curso no banco de dados
        curso = cursoRepository.save(curso);

        // Retorna a resposta DTO
        return cursoMapper.toResponseDTO(curso);
    }

    // Atualiza um curso existente
    public CursoResponse atualizarCurso(Long id, CursoRequest cursoRequestDTO) {
        // Verifica se o curso existe
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Curso não encontrado"));

        // Verifica se o campusId existe
        Optional<Campus> campus = campusRepository.findById(cursoRequestDTO.campusId());
        if (campus.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Campus não encontrado");
        }

        // Atualiza a entidade
        curso.setName(cursoRequestDTO.name());
        curso.setSigla(cursoRequestDTO.sigla());
        curso.setCampus(campus.get());
        curso.setUpdateDate(); // Atualiza a data de atualização

        // Salva o curso atualizado
        curso = cursoRepository.save(curso);

        // Retorna a resposta DTO
        return cursoMapper.toResponseDTO(curso);
    }

    // Obtém todos os cursos
    public List<CursoResponse> obterTodosCursos() {
        List<Curso> cursos = cursoRepository.findAll();
        return cursos.stream()
                .map(cursoMapper::toResponseDTO)
                .toList();
    }

    // Obtém um curso específico por ID
    public CursoResponse obterCursoPorId(Long id) {
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Curso não encontrado"));
        return cursoMapper.toResponseDTO(curso);
    }

    // Exclui um curso por ID
    public void excluirCurso(Long id) {
        if (!cursoRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Curso não encontrado");
        }
        cursoRepository.deleteById(id);
    }
}
