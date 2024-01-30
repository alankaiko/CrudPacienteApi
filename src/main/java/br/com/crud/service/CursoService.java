package br.com.crud.service;

import br.com.crud.acore.service.AbstractService;
import br.com.crud.model.Curso;
import br.com.crud.model.filter.CursoFiltro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CursoService extends AbstractService<Curso, CursoFiltro> {
    Page<Curso> paginado(CursoFiltro cursoFiltro, Pageable pageable);
}
