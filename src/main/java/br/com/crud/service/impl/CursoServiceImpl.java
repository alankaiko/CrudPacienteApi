package br.com.crud.service.impl;

import br.com.crud.acore.service.impl.AbstractServiceImpl;
import br.com.crud.model.Curso;
import br.com.crud.model.filter.CursoFiltro;
import br.com.crud.repository.CursoRepository;
import br.com.crud.service.CursoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CursoServiceImpl extends AbstractServiceImpl<Curso, CursoFiltro> implements CursoService {
    private final CursoRepository cursoRepository;

    public CursoServiceImpl(CursoRepository cursoRepository) {
        super(cursoRepository);
        this.cursoRepository = cursoRepository;
    }

    @Override
    public Page<Curso> paginado(CursoFiltro cursoFiltro, Pageable pageable) {
        return this.cursoRepository.paginado(cursoFiltro, pageable);
    }
}
