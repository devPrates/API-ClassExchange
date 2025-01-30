package com.ClassExchange.uc.manter_cursos;

import com.ClassExchange.uc.manter_campus.Campus;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "tb_cursos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cursoId;

    private String name;
    private String sigla;

    // Relacionamento ManyToOne com Campus
    @ManyToOne
    @JoinColumn(name = "campus_id") // Nome da coluna de chave estrangeira para Campus
    private Campus campus;

    // Campos de data/hora
    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

    // Método para atribuir valores durante a criação
    public void setCreateDate() {
        this.createdAt = Instant.now();
    }

    // Método para atribuir valores durante a atualização
    public void setUpdateDate() {
        this.updatedAt = Instant.now();
    }

    // Construtor para instanciar com o id
    public Curso(Long cursoId) {
        this.cursoId = cursoId;
    }
}
