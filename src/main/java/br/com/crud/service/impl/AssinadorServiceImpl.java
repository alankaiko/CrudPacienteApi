package br.com.crud.service.impl;

import br.com.crud.acore.service.impl.AbstractServiceImpl;
import br.com.crud.model.Assinador;
import br.com.crud.model.filter.AssinadorFiltro;
import br.com.crud.repository.AssinadorRepository;
import br.com.crud.service.AssinadorService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AssinadorServiceImpl extends AbstractServiceImpl<Assinador, AssinadorFiltro> implements AssinadorService {
    private final AssinadorRepository assinadorRepository;

    public AssinadorServiceImpl(AssinadorRepository assinadorRepository) {
        super(assinadorRepository);
        this.assinadorRepository = assinadorRepository;
    }

    @Override
    public Page<Assinador> paginado(AssinadorFiltro assinadorFiltro, Pageable pageable) {
        return this.assinadorRepository.paginado(assinadorFiltro, pageable);
    }
}
