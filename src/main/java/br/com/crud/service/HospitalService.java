package br.com.crud.service;

import br.com.crud.acore.service.AbstractService;
import br.com.crud.model.Hospital;
import br.com.crud.model.filter.HospitalFiltro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HospitalService extends AbstractService<Hospital, HospitalFiltro> {
    Page<Hospital> paginado(HospitalFiltro hospitalFiltro, Pageable pageable);
}
