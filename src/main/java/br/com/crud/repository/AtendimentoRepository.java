package br.com.crud.repository;

import br.com.crud.acore.repository.AbstractRepository;
import br.com.crud.model.Atendimento;
import br.com.crud.model.filter.AtendimentoFiltro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface AtendimentoRepository extends AbstractRepository<Atendimento, AtendimentoFiltro, Long> {
    Page<Atendimento> paginado(AtendimentoFiltro atendimentoFiltro, Pageable pageable);
}
