package com.ClassExchange.uc.manter_turmas;

import com.ClassExchange.uc.manter_cursos.Curso;
import com.ClassExchange.uc.manter_cursos.CursoRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses = {})
public abstract class TurmaMapper {

    // Injeção do repositório de Curso para poder buscar o curso pelo ID
    @Autowired
    private CursoRepository cursoRepository;

    /**
     * Mapeia uma entidade Turma para um DTO de resposta (TurmaResponseDTO).
     * @param turma A entidade Turma que será convertida para DTO.
     * @return Um TurmaResponseDTO com os dados da turma.
     */
    @Mapping(source = "curso.cursoId", target = "cursoId")
    public abstract TurmaResponse toResponseDTO(Turma turma);

    /**
     * Mapeia um DTO de requisição (TurmaRequestDTO) para uma entidade Turma.
     * @param dto O DTO de requisição com os dados para criar ou atualizar uma Turma.
     * @return A entidade Turma, com o Curso atribuído a partir do cursoId.
     */
    @Mapping(source = "cursoId", target = "curso", qualifiedByName = "mapCursoIdToCurso")
    public abstract Turma toEntity(TurmaRequest dto);

    /**
     * Converte o cursoId (Long) para uma entidade Curso.
     * Este método é chamado pelo MapStruct para buscar o Curso no banco de dados.
     * @param cursoId O ID do curso a ser buscado.
     * @return A entidade Curso correspondente ao ID fornecido.
     * @throws IllegalArgumentException Se o Curso com o ID fornecido não for encontrado.
     */
    @Named("mapCursoIdToCurso")
    protected Curso mapCursoIdToCurso(Long cursoId) {
        return cursoId == null ? null : cursoRepository.findById(cursoId).orElseThrow(
                () -> new IllegalArgumentException("Curso com ID " + cursoId + " não encontrado.")
        );
    }
}
