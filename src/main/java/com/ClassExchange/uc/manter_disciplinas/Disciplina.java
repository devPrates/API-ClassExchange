package com.ClassExchange.uc.manter_disciplinas;

import com.ClassExchange.uc.manter_periodos.Periodo;
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

    private String nome;
    private int cargaHoraria;

    @ManyToOne
    @JoinColumn(name = "periodo_id", nullable = false)
    private Periodo periodo;
}
