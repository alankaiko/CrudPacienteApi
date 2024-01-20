package br.com.crud.service.impl;

import br.com.crud.acore.service.impl.AbstractServiceImpl;
import br.com.crud.model.Hospital;
import br.com.crud.model.filter.HospitalFiltro;
import br.com.crud.repository.HospitalRepository;
import br.com.crud.service.HospitalService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class HospitalServiceImpl extends AbstractServiceImpl<Hospital, HospitalFiltro> implements HospitalService {
    private final HospitalRepository convenioRepository;

    public HospitalServiceImpl(HospitalRepository convenioRepository) {
        super(convenioRepository);
        this.convenioRepository = convenioRepository;
    }

    @Override
    public Page<Hospital> paginado(HospitalFiltro hospitalFiltro, Pageable pageable) {
        return this.convenioRepository.paginado(hospitalFiltro, pageable);
    }
}
