package br.com.crud.repository.impl;

import br.com.crud.acore.repository.impl.AbstractRepositoryImpl;
import br.com.crud.model.Hospital;
import br.com.crud.model.Hospital_;
import br.com.crud.model.filter.HospitalFiltro;
import br.com.crud.repository.HospitalRepository;
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
public class HospitalRepositoryImpl extends AbstractRepositoryImpl<Hospital, HospitalFiltro, Long> implements HospitalRepository {
    private final EntityManager entityManager;

    public HospitalRepositoryImpl(EntityManager entityManager) {
        super(Hospital.class, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public Page<Hospital> paginado(HospitalFiltro hospitalFiltro, Pageable pageable) {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Hospital> query = builder.createQuery(Hospital.class);
        Root<Hospital> root = query.from(Hospital.class);

        query.orderBy(builder.asc(root.get("id")));
        Predicate[] predicato = this.adicionarFiltros(builder, hospitalFiltro, root);
        query.where(predicato);

        TypedQuery<Hospital> tiped = this.entityManager.createQuery(query);
        this.adicionarPaginacao(tiped, pageable);

        return new PageImpl<>(tiped.getResultList(), pageable, this.total(hospitalFiltro));
    }

    private Predicate[] adicionarFiltros(CriteriaBuilder builder, HospitalFiltro hospitalFiltro, Root<Hospital> root) {
        List<Predicate> lista = new ArrayList<>();

        if (!StringUtils.isEmpty(hospitalFiltro.getNome()))
            lista.add(builder.like(builder.lower(root.get(Hospital_.NOME)), "%" + hospitalFiltro.getNome().toLowerCase() + "%"));

        return lista.toArray(new Predicate[lista.size()]);
    }

    private Long total(HospitalFiltro hospitalFiltro) {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<Hospital> root = query.from(Hospital.class);

        Predicate[] predicato = adicionarFiltros(builder, hospitalFiltro, root);
        query.where(predicato);
        query.select(builder.count(root));
        return this.entityManager.createQuery(query).getSingleResult();
    }
}
