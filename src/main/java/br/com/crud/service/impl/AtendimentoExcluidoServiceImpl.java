package br.com.crud.service.impl;

import br.com.crud.acore.service.impl.AbstractServiceImpl;
import br.com.crud.model.AtendimentoExcluido;
import br.com.crud.model.filter.AtendimentoExcluidoFiltro;
import br.com.crud.repository.AtendimentoExcluidoRepository;
import br.com.crud.service.AtendimentoExcluidoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AtendimentoExcluidoServiceImpl extends AbstractServiceImpl<AtendimentoExcluido, AtendimentoExcluidoFiltro> implements AtendimentoExcluidoService {
    private final AtendimentoExcluidoRepository atendimentoExcluidoRepository;

    public AtendimentoExcluidoServiceImpl(AtendimentoExcluidoRepository atendimentoExcluidoRepository) {
        super(atendimentoExcluidoRepository);
        this.atendimentoExcluidoRepository = atendimentoExcluidoRepository;
    }

    @Override
    public Page<AtendimentoExcluido> paginado(AtendimentoExcluidoFiltro atendimentoExcluidoFiltro, Pageable pageable) {
        return this.atendimentoExcluidoRepository.paginado(atendimentoExcluidoFiltro, pageable);
    }
}
