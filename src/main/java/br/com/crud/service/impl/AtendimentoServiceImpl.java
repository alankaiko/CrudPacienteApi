package br.com.crud.service.impl;

import br.com.crud.acore.service.impl.AbstractServiceImpl;
import br.com.crud.model.Atendimento;
import br.com.crud.model.filter.AtendimentoFiltro;
import br.com.crud.repository.AtendimentoRepository;
import br.com.crud.service.AtendimentoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AtendimentoServiceImpl extends AbstractServiceImpl<Atendimento, AtendimentoFiltro> implements AtendimentoService {
    private final AtendimentoRepository atendimentoRepository;

    public AtendimentoServiceImpl(AtendimentoRepository atendimentoRepository) {
        super(atendimentoRepository);
        this.atendimentoRepository = atendimentoRepository;
    }

    @Override
    public Page<Atendimento> paginado(AtendimentoFiltro atendimentoFiltro, Pageable pageable) {
        return this.atendimentoRepository.paginado(atendimentoFiltro, pageable);
    }
}
