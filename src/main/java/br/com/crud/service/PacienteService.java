package br.com.crud.service;

import br.com.crud.acore.service.AbstractService;
import br.com.crud.model.Paciente;
import br.com.crud.model.filter.PacienteFiltro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PacienteService extends AbstractService<Paciente, PacienteFiltro> {
    Page<Paciente> paginado(PacienteFiltro pacienteFiltro, Pageable pageable);
}
