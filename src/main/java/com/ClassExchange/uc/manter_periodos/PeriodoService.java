package com.ClassExchange.uc.manter_periodos;

import com.ClassExchange.uc.manter_turmas.Turma;
import com.ClassExchange.uc.manter_turmas.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PeriodoService {

    @Autowired
    private PeriodoRepository periodoRepository;

    @Autowired
    private TurmaRepository turmaRepository;



    @Transactional
    public PeriodoResponse createPeriodo(PeriodoRequest request) {
        Turma turma = turmaRepository.findById(request.turmaId())
                .orElseThrow(() -> new IllegalArgumentException("Turma não encontrada"));

        Periodo periodo = PeriodoMapper.INSTANCE.toEntity(request);
        periodo.setTurma(turma);
        periodo = periodoRepository.save(periodo);

        return PeriodoMapper.INSTANCE.toResponse(periodo);
    }

    public List<PeriodoResponse> getAllPeriodos() {
        return periodoRepository.findAll().stream()
                .map(PeriodoMapper.INSTANCE::toResponse)
                .toList();
    }

    public PeriodoResponse getPeriodoById(Long id) {
        Periodo periodo = periodoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Período não encontrado"));
        return PeriodoMapper.INSTANCE.toResponse(periodo);
    }

    @Transactional
    public PeriodoResponse updatePeriodo(Long id, PeriodoRequest request) {
        Periodo periodo = periodoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Período não encontrado"));

        Turma turma = turmaRepository.findById(request.turmaId())
                .orElseThrow(() -> new IllegalArgumentException("Turma não encontrada"));

        periodo.setNome(request.nome());
        periodo.setTipoPeriodo(request.tipoPeriodo());
        periodo.setNumero(request.numero());
        periodo.setAno(request.ano());
        periodo.setInicio(request.inicio());
        periodo.setFim(request.fim());
        periodo.setTurma(turma);

        periodo = periodoRepository.save(periodo);
        return PeriodoMapper.INSTANCE.toResponse(periodo);
    }

    @Transactional
    public void deletePeriodo(Long id) {
        if (!periodoRepository.existsById(id)) {
            throw new IllegalArgumentException("Período não encontrado");
        }
        periodoRepository.deleteById(id);
    }
}