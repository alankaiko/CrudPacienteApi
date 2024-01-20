package br.com.crud.repository;

import br.com.crud.acore.repository.AbstractRepository;
import br.com.crud.model.Hospital;
import br.com.crud.model.filter.HospitalFiltro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface HospitalRepository extends AbstractRepository<Hospital, HospitalFiltro, Long> {
    Page<Hospital> paginado(HospitalFiltro hospitalFiltro, Pageable pageable);
}
