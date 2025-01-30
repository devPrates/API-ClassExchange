package com.ClassExchange.uc.manter_periodos;

import com.ClassExchange.uc.manter_disciplinas.Disciplina;
import com.ClassExchange.uc.manter_turmas.Turma;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_periodos")
public class Periodo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "periodo_id")
    private Long periodoId;

    private String nome;

    private String tipoPeriodo;

    private int numero;

    private int ano;

    private Instant inicio;

    private Instant fim;

    @ManyToOne
    @JoinColumn(name = "turma_id", nullable = false)
    private Turma turma;

    @OneToMany(mappedBy = "periodo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Disciplina> disciplinas;
}
