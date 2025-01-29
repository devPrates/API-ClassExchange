package com.ClassExchange.uc.manter_cursos;

import com.ClassExchange.uc.manter_campus.Campus;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tb_curso")
@Data
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "curso_id")
    private Long cursoId;

    private String name;

    private String sigla;

    @ManyToOne
    @JoinColumn(name = "campus_id", nullable = false)
    private Campus campus;
}
