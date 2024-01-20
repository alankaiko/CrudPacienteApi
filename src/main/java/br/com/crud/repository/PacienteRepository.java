package br.com.crud.repository;

import br.com.crud.acore.repository.AbstractRepository;
import br.com.crud.model.Paciente;
import br.com.crud.model.filter.PacienteFiltro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface PacienteRepository extends AbstractRepository<Paciente, PacienteFiltro, Long> {
    Page<Paciente> paginado(PacienteFiltro pacienteFiltro, Pageable pageable);
}
