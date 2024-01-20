package br.com.crud.repository.impl;

import br.com.crud.acore.repository.impl.AbstractRepositoryImpl;
import br.com.crud.model.Paciente;
import br.com.crud.model.Paciente_;
import br.com.crud.model.filter.PacienteFiltro;
import br.com.crud.repository.PacienteRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class PacienteRepositoryImpl extends AbstractRepositoryImpl<Paciente, PacienteFiltro, Long> implements PacienteRepository {
    private final EntityManager entityManager;

    public PacienteRepositoryImpl(EntityManager entityManager) {
        super(Paciente.class, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public Page<Paciente> paginado(PacienteFiltro pacienteFiltro, Pageable pageable) {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Paciente> query = builder.createQuery(Paciente.class);
        Root<Paciente> root = query.from(Paciente.class);

        query.orderBy(builder.asc(root.get("codigo")));
        Predicate[] predicato = this.adicionarFiltros(builder, pacienteFiltro, root);
        query.where(predicato);

        TypedQuery<Paciente> tiped = this.entityManager.createQuery(query);
        this.adicionarPaginacao(tiped, pageable);

        return new PageImpl<>(tiped.getResultList(), pageable, this.total(pacienteFiltro));
    }

    private Predicate[] adicionarFiltros(CriteriaBuilder builder, PacienteFiltro pacienteFiltro, Root<Paciente> root) {
        List<Predicate> lista = new ArrayList<>();

        if (!StringUtils.isEmpty(pacienteFiltro.getNome()))
            lista.add(builder.like(builder.lower(root.get(Paciente_.NOME)), "%" + pacienteFiltro.getNome().toLowerCase() + "%"));

        return lista.toArray(new Predicate[lista.size()]);
    }

    private void adicionarPaginacao(TypedQuery<?> tiped, Pageable page) {
        int paginaatual = page.getPageNumber();
        int totalporpagina = page.getPageSize();
        int primeiroRegistroDaPagina = paginaatual * totalporpagina;

        tiped.setFirstResult(primeiroRegistroDaPagina);
        tiped.setMaxResults(totalporpagina);
    }

    private Long total(PacienteFiltro pacienteFiltro) {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<Paciente> root = query.from(Paciente.class);

        Predicate[] predicato = adicionarFiltros(builder, pacienteFiltro, root);
        query.where(predicato);
        query.select(builder.count(root));
        return this.entityManager.createQuery(query).getSingleResult();
    }
}
