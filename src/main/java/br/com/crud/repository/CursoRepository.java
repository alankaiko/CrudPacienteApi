package br.com.crud.repository;

import br.com.crud.acore.repository.AbstractRepository;
import br.com.crud.model.Curso;
import br.com.crud.model.filter.CursoFiltro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface CursoRepository extends AbstractRepository<Curso, CursoFiltro, Long> {
    Page<Curso> paginado(CursoFiltro cursoFiltro, Pageable pageable);
}
