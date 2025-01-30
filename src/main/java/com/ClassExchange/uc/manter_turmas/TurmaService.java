package com.ClassExchange.uc.manter_turmas;

import com.ClassExchange.uc.manter_cursos.Curso;
import com.ClassExchange.uc.manter_cursos.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TurmaService {

    @Autowired
    private TurmaRepository turmaRepository;

    @Autowired
    private TurmaMapper turmaMapper;

    @Autowired
    private CursoRepository cursoRepository;

    /**
     * Cria uma nova turma no sistema.
     * Recebe um DTO de requisição e converte para a entidade Turma,
     * em seguida salva a turma no banco de dados.
     * @param turmaRequestDTO O DTO com os dados da nova turma.
     * @return O DTO da turma criada.
     */
    @Transactional
    public TurmaResponse createTurma(TurmaRequest turmaRequestDTO) {
        // Converte o DTO para a entidade
        Turma turma = turmaMapper.toEntity(turmaRequestDTO);

        // Salva a turma no banco de dados
        Turma savedTurma = turmaRepository.save(turma);

        // Retorna o DTO da turma salva
        return turmaMapper.toResponseDTO(savedTurma);
    }

    /**
     * Lista todas as turmas cadastradas no sistema.
     * @return Uma lista de DTOs de turmas.
     */
    public List<TurmaResponse> getAllTurmas() {
        // Busca todas as turmas do banco
        List<Turma> turmas = turmaRepository.findAll();

        // Converte para DTOs e retorna
        return turmas.stream()
                .map(turmaMapper::toResponseDTO)
                .toList();
    }

    /**
     * Busca uma turma pelo ID.
     * @param turmaId O ID da turma que será buscada.
     * @return O DTO da turma encontrada, ou uma exceção caso não encontre.
     */
    public TurmaResponse getTurmaById(Long turmaId) {
        // Busca a turma no banco
        Optional<Turma> turmaOpt = turmaRepository.findById(turmaId);

        // Se a turma não for encontrada, lança uma exceção
        Turma turma = turmaOpt.orElseThrow(() ->
                new IllegalArgumentException("Turma com ID " + turmaId + " não encontrada.")
        );

        // Retorna o DTO da turma encontrada
        return turmaMapper.toResponseDTO(turma);
    }

    /**
     * Atualiza os dados de uma turma existente.
     * @param turmaId O ID da turma que será atualizada.
     * @param turmaRequestDTO O DTO com os novos dados da turma.
     * @return O DTO da turma atualizada.
     */
    @Transactional
    public TurmaResponse updateTurma(Long turmaId, TurmaRequest turmaRequestDTO) {
        // Verifica se a turma existe
        Turma turma = turmaRepository.findById(turmaId).orElseThrow(() ->
                new IllegalArgumentException("Turma com ID " + turmaId + " não encontrada.")
        );

        // Se o cursoId for fornecido, busca o curso no repositório
        if (turmaRequestDTO.cursoId() != null) {
            Curso curso = cursoRepository.findById(turmaRequestDTO.cursoId()).orElseThrow(() ->
                    new IllegalArgumentException("Curso com ID " + turmaRequestDTO.cursoId() + " não encontrado.")
            );
            turma.setCurso(curso);
        }

        // Atualiza os outros campos
        turma.setNome(turmaRequestDTO.nome());
        turma.setAno(turmaRequestDTO.ano());

        // Salva as mudanças no banco de dados
        Turma updatedTurma = turmaRepository.save(turma);

        // Retorna o DTO da turma atualizada
        return turmaMapper.toResponseDTO(updatedTurma);
    }

    /**
     * Deleta uma turma do sistema.
     * @param turmaId O ID da turma a ser deletada.
     */
    @Transactional
    public void deleteTurma(Long turmaId) {
        // Verifica se a turma existe
        Turma turma = turmaRepository.findById(turmaId).orElseThrow(() ->
                new IllegalArgumentException("Turma com ID " + turmaId + " não encontrada.")
        );

        // Deleta a turma do banco de dados
        turmaRepository.delete(turma);
    }
}
