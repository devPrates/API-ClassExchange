package com.ClassExchange.uc.manter_campus;

import com.ClassExchange.uc.manter_cursos.Curso;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "tb_campus")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Campus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "campus_id")
    private Long campusId;

    private String name;

    private String sigla;

    private String endereco;

    @OneToMany(mappedBy = "campus", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Curso> cursos;

    public Campus(String campusA, String ca, String endereçoA) {
    }
}
