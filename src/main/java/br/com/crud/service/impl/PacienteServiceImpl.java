package br.com.crud.service.impl;

import br.com.crud.acore.service.impl.AbstractServiceImpl;
import br.com.crud.model.Paciente;
import br.com.crud.model.filter.PacienteFiltro;
import br.com.crud.repository.PacienteRepository;
import br.com.crud.service.PacienteService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PacienteServiceImpl extends AbstractServiceImpl<Paciente, PacienteFiltro> implements PacienteService {
    private final PacienteRepository pacienteRepository;

    public PacienteServiceImpl(PacienteRepository pacienteRepository) {
        super(pacienteRepository);
        this.pacienteRepository = pacienteRepository;
    }

    @Override
    public Page<Paciente> paginado(PacienteFiltro pacienteFiltro, Pageable pageable) {
        return this.pacienteRepository.paginado(pacienteFiltro, pageable);
    }
}
