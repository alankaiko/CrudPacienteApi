package br.com.crud.service;

import br.com.crud.acore.service.AbstractService;
import br.com.crud.model.Convenio;
import br.com.crud.model.filter.ConvenioFiltro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ConvenioService extends AbstractService<Convenio, ConvenioFiltro> {
    Page<Convenio> paginado(ConvenioFiltro convenioFiltro, Pageable pageable);
}
