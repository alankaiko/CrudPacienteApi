package br.com.crud.repository;

import br.com.crud.acore.repository.AbstractRepository;
import br.com.crud.model.Medico;
import br.com.crud.model.filter.MedicoFiltro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface MedicoRepository extends AbstractRepository<Medico, MedicoFiltro, Long> {
    Page<Medico> paginado(MedicoFiltro medicoFiltro, Pageable pageable);
}
