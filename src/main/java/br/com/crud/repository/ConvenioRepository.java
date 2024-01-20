package br.com.crud.repository;

import br.com.crud.acore.repository.AbstractRepository;
import br.com.crud.model.Convenio;
import br.com.crud.model.filter.ConvenioFiltro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface ConvenioRepository extends AbstractRepository<Convenio, ConvenioFiltro, Long> {
    Page<Convenio> paginado(ConvenioFiltro convenioFiltro, Pageable pageable);
}
