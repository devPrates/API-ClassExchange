package com.ClassExchange.uc.manter_disciplinas;

import com.ClassExchange.uc.manter_periodos.Periodo;
import com.ClassExchange.uc.manter_periodos.PeriodoRepository;
import com.ClassExchange.uc.manter_turmas.Turma;
import com.ClassExchange.uc.manter_turmas.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DisciplinaService {

    private final DisciplinaRepository disciplinaRepository;
    private final PeriodoRepository periodoRepository;
    private final TurmaRepository turmaRepository;
    private final DisciplinaMapper disciplinaMapper;

    public DisciplinaService(DisciplinaRepository disciplinaRepository,
                             PeriodoRepository periodoRepository,
                             TurmaRepository turmaRepository,
                             DisciplinaMapper disciplinaMapper) {
        this.disciplinaRepository = disciplinaRepository;
        this.periodoRepository = periodoRepository;
        this.turmaRepository = turmaRepository;
        this.disciplinaMapper = disciplinaMapper;
    }

    /**
     * Lista todas as disciplinas cadastradas no banco de dados.
     */
    public List<DisciplinaResponse> getAllDisciplinas() {
        return disciplinaRepository.findAll()
                .stream()
                .map(disciplinaMapper::toResponse)
                .collect(Collectors.toList());
    }

    /**
     * Busca uma disciplina pelo ID. Se não encontrar, lança uma exceção.
     */
    public DisciplinaResponse getDisciplinaById(Long disciplinaId) {
        Disciplina disciplina = disciplinaRepository.findById(disciplinaId)
                .orElseThrow(() -> new RuntimeException("Disciplina não encontrada com o ID: " + disciplinaId));
        return disciplinaMapper.toResponse(disciplina);
    }

    /**
     * Cria uma nova disciplina associada a um período e uma turma.
     */
    public DisciplinaResponse createDisciplina(DisciplinaRequest disciplinaRequestDTO) {
        Periodo periodo = periodoRepository.findById(disciplinaRequestDTO.periodoId())
                .orElseThrow(() -> new RuntimeException("Período não encontrado com o ID: " + disciplinaRequestDTO.periodoId()));

        Turma turma = turmaRepository.findById(disciplinaRequestDTO.turmaId())
                .orElseThrow(() -> new RuntimeException("Turma não encontrada com o ID: " + disciplinaRequestDTO.turmaId()));

        Disciplina disciplina = disciplinaMapper.toEntity(disciplinaRequestDTO);
        disciplina.setPeriodo(periodo);
        disciplina.setTurma(turma);

        return disciplinaMapper.toResponse(disciplinaRepository.save(disciplina));
    }

    /**
     * Atualiza os dados de uma disciplina existente.
     */
    public DisciplinaResponse updateDisciplina(Long disciplinaId, DisciplinaRequest disciplinaRequestDTO) {
        Disciplina disciplina = disciplinaRepository.findById(disciplinaId)
                .orElseThrow(() -> new RuntimeException("Disciplina não encontrada com o ID: " + disciplinaId));

        disciplina.setNome(disciplinaRequestDTO.nome());
        disciplina.setCargaHoraria(disciplinaRequestDTO.cargaHoraria());

        if (disciplinaRequestDTO.periodoId() != null) {
            Periodo periodo = periodoRepository.findById(disciplinaRequestDTO.periodoId())
                    .orElseThrow(() -> new RuntimeException("Período não encontrado com o ID: " + disciplinaRequestDTO.periodoId()));
            disciplina.setPeriodo(periodo);
        }

        if (disciplinaRequestDTO.turmaId() != null) {
            Turma turma = turmaRepository.findById(disciplinaRequestDTO.turmaId())
                    .orElseThrow(() -> new RuntimeException("Turma não encontrada com o ID: " + disciplinaRequestDTO.turmaId()));
            disciplina.setTurma(turma);
        }

        return disciplinaMapper.toResponse(disciplinaRepository.save(disciplina));
    }

    /**
     * Deleta uma disciplina pelo ID.
     */
    public void deleteDisciplina(Long disciplinaId) {
        if (!disciplinaRepository.existsById(disciplinaId)) {
            throw new RuntimeException("Disciplina não encontrada com o ID: " + disciplinaId);
        }
        disciplinaRepository.deleteById(disciplinaId);
    }
}