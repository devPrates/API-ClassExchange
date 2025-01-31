package com.ClassExchange.uc.manter_disciplinas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {
    List<Disciplina> findByTurma_TurmaId(Long turmaId);
    List<Disciplina> findByPeriodo_PeriodoId(Long periodoId);
}