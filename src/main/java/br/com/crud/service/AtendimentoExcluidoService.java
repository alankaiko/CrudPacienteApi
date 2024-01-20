package br.com.crud.service;

import br.com.crud.acore.service.AbstractService;
import br.com.crud.model.AtendimentoExcluido;
import br.com.crud.model.filter.AtendimentoExcluidoFiltro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AtendimentoExcluidoService extends AbstractService<AtendimentoExcluido, AtendimentoExcluidoFiltro> {
    Page<AtendimentoExcluido> paginado(AtendimentoExcluidoFiltro atendimentoExcluidoFiltro, Pageable pageable);
}
