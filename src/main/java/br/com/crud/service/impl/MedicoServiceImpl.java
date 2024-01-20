package br.com.crud.service.impl;

import br.com.crud.acore.service.impl.AbstractServiceImpl;
import br.com.crud.model.Medico;
import br.com.crud.model.filter.MedicoFiltro;
import br.com.crud.repository.MedicoRepository;
import br.com.crud.service.MedicoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MedicoServiceImpl extends AbstractServiceImpl<Medico, MedicoFiltro> implements MedicoService {
    private final MedicoRepository medicoRepository;

    public MedicoServiceImpl(MedicoRepository medicoRepository) {
        super(medicoRepository);
        this.medicoRepository = medicoRepository;
    }

    @Override
    public Page<Medico> paginado(MedicoFiltro medicoFiltro, Pageable pageable) {
        return this.medicoRepository.paginado(medicoFiltro, pageable);
    }
}
