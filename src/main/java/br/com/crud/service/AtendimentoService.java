package br.com.crud.service;

import br.com.crud.acore.service.AbstractService;
import br.com.crud.model.Atendimento;
import br.com.crud.model.filter.AtendimentoFiltro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AtendimentoService extends AbstractService<Atendimento, AtendimentoFiltro> {
    Page<Atendimento> paginado(AtendimentoFiltro atendimentoFiltro, Pageable pageable);
}
