package br.com.crud.repository.impl;

import br.com.crud.acore.repository.impl.AbstractRepositoryImpl;
import br.com.crud.model.Medico;
import br.com.crud.model.Medico_;
import br.com.crud.model.filter.MedicoFiltro;
import br.com.crud.repository.MedicoRepository;
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
public class MedicoRepositoryImpl extends AbstractRepositoryImpl<Medico, MedicoFiltro, Long> implements MedicoRepository {
    private final EntityManager entityManager;

    public MedicoRepositoryImpl(EntityManager entityManager) {
        super(Medico.class, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public Page<Medico> paginado(MedicoFiltro medicoFiltro, Pageable pageable) {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Medico> query = builder.createQuery(Medico.class);
        Root<Medico> root = query.from(Medico.class);

        query.orderBy(builder.asc(root.get("id")));
        Predicate[] predicato = this.adicionarFiltros(builder, medicoFiltro, root);
        query.where(predicato);

        TypedQuery<Medico> tiped = this.entityManager.createQuery(query);
        this.adicionarPaginacao(tiped, pageable);

        return new PageImpl<>(tiped.getResultList(), pageable, this.total(medicoFiltro));
    }

    private Predicate[] adicionarFiltros(CriteriaBuilder builder, MedicoFiltro medicoFiltro, Root<Medico> root) {
        List<Predicate> lista = new ArrayList<>();

        if (!StringUtils.isEmpty(medicoFiltro.getNome()))
            lista.add(builder.like(builder.lower(root.get(Medico_.NOME)), "%" + medicoFiltro.getNome().toLowerCase() + "%"));

        return lista.toArray(new Predicate[lista.size()]);
    }

    private void adicionarPaginacao(TypedQuery<?> tiped, Pageable page) {
        int paginaatual = page.getPageNumber();
        int totalporpagina = page.getPageSize();
        int primeiroRegistroDaPagina = paginaatual * totalporpagina;

        tiped.setFirstResult(primeiroRegistroDaPagina);
        tiped.setMaxResults(totalporpagina);
    }

    private Long total(MedicoFiltro medicoFiltro) {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<Medico> root = query.from(Medico.class);

        Predicate[] predicato = adicionarFiltros(builder, medicoFiltro, root);
        query.where(predicato);
        query.select(builder.count(root));
        return this.entityManager.createQuery(query).getSingleResult();
    }
}
