package br.com.crud.service;

import br.com.crud.acore.service.AbstractService;
import br.com.crud.model.Medico;
import br.com.crud.model.filter.MedicoFiltro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MedicoService extends AbstractService<Medico, MedicoFiltro> {
    Page<Medico> paginado(MedicoFiltro medicoFiltro, Pageable pageable);
}
