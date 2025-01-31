package com.ClassExchange.uc.manter_disciplinas;

import com.ClassExchange.uc.manter_periodos.Periodo;
import com.ClassExchange.uc.manter_turmas.Turma;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_disciplinas")
public class Disciplina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "disciplina_id")
    private Long disciplinaId;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)


    private int cargaHoraria;

    @ManyToOne
    @JoinColumn(name = "periodo_id", nullable = false)
    private Periodo periodo;

    @ManyToOne
    @JoinColumn(name = "turma_id", nullable = false)
    private Turma turma;

    public Disciplina(Long disciplinaId) {
        this.disciplinaId = disciplinaId;
    }
}
