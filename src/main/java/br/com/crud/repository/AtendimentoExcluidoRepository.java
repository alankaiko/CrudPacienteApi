package br.com.crud.repository;

import br.com.crud.acore.repository.AbstractRepository;
import br.com.crud.model.AtendimentoExcluido;
import br.com.crud.model.filter.AtendimentoExcluidoFiltro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface AtendimentoExcluidoRepository extends AbstractRepository<AtendimentoExcluido, AtendimentoExcluidoFiltro, Long> {
    Page<AtendimentoExcluido> paginado(AtendimentoExcluidoFiltro atendimentoExcluidoFiltro, Pageable pageable);
}
