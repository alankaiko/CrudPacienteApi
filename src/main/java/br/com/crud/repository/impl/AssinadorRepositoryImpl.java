package br.com.crud.repository.impl;

import br.com.crud.acore.repository.impl.AbstractRepositoryImpl;
import br.com.crud.model.Assinador;
import br.com.crud.model.filter.AssinadorFiltro;
import br.com.crud.repository.AssinadorRepository;
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
public class AssinadorRepositoryImpl extends AbstractRepositoryImpl<Assinador, AssinadorFiltro, Long> implements AssinadorRepository {
    private final EntityManager entityManager;

    public AssinadorRepositoryImpl(EntityManager entityManager) {
        super(Assinador.class, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public Page<Assinador> paginado(AssinadorFiltro assinadorFiltro, Pageable pageable) {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Assinador> query = builder.createQuery(Assinador.class);
        Root<Assinador> root = query.from(Assinador.class);

        query.orderBy(builder.asc(root.get("id")));
        Predicate[] predicato = this.adicionarFiltros(builder, assinadorFiltro, root);
        query.where(predicato);

        TypedQuery<Assinador> tiped = this.entityManager.createQuery(query);
        this.adicionarPaginacao(tiped, pageable);

        return new PageImpl<>(tiped.getResultList(), pageable, this.total(assinadorFiltro));
    }

    private Predicate[] adicionarFiltros(CriteriaBuilder builder, AssinadorFiltro assinadorFiltro, Root<Assinador> root) {
        List<Predicate> lista = new ArrayList<>();

        if (!StringUtils.isEmpty(assinadorFiltro.getNome()))
            lista.add(builder.like(builder.lower(root.get("nome")), "%" + assinadorFiltro.getNome().toLowerCase() + "%"));

        return lista.toArray(new Predicate[lista.size()]);
    }

    private Long total(AssinadorFiltro assinadorFiltro) {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<Assinador> root = query.from(Assinador.class);

        Predicate[] predicato = adicionarFiltros(builder, assinadorFiltro, root);
        query.where(predicato);
        query.select(builder.count(root));
        return this.entityManager.createQuery(query).getSingleResult();
    }
}
