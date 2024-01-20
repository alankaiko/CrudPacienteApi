package br.com.crud.repository;

import br.com.crud.acore.repository.AbstractRepository;
import br.com.crud.model.Assinador;
import br.com.crud.model.filter.AssinadorFiltro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface AssinadorRepository extends AbstractRepository<Assinador, AssinadorFiltro, Long> {
    Page<Assinador> paginado(AssinadorFiltro assinadorFiltro, Pageable pageable);
}
