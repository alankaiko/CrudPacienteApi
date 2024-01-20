package br.com.crud.service.impl;

import br.com.crud.acore.service.impl.AbstractServiceImpl;
import br.com.crud.model.Convenio;
import br.com.crud.model.filter.ConvenioFiltro;
import br.com.crud.repository.ConvenioRepository;
import br.com.crud.service.ConvenioService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ConvenioServiceImpl extends AbstractServiceImpl<Convenio, ConvenioFiltro> implements ConvenioService {
    private final ConvenioRepository convenioRepository;

    public ConvenioServiceImpl(ConvenioRepository convenioRepository) {
        super(convenioRepository);
        this.convenioRepository = convenioRepository;
    }

    @Override
    public Page<Convenio> paginado(ConvenioFiltro convenioFiltro, Pageable pageable) {
        return this.convenioRepository.paginado(convenioFiltro, pageable);
    }
}
