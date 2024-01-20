package br.com.crud.service;

import br.com.crud.acore.service.AbstractService;
import br.com.crud.model.Assinador;
import br.com.crud.model.filter.AssinadorFiltro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AssinadorService extends AbstractService<Assinador, AssinadorFiltro> {
    Page<Assinador> paginado(AssinadorFiltro assinadorFiltro, Pageable pageable);
}
