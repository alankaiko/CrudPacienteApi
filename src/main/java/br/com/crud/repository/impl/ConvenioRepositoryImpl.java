package br.com.crud.repository.impl;

import br.com.crud.acore.repository.impl.AbstractRepositoryImpl;
import br.com.crud.model.Convenio;
import br.com.crud.model.Convenio_;
import br.com.crud.model.filter.ConvenioFiltro;
import br.com.crud.repository.ConvenioRepository;
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
public class ConvenioRepositoryImpl extends AbstractRepositoryImpl<Convenio, ConvenioFiltro, Long> implements ConvenioRepository {
    private final EntityManager entityManager;

    public ConvenioRepositoryImpl(EntityManager entityManager) {
        super(Convenio.class, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public Page<Convenio> paginado(ConvenioFiltro convenioFiltro, Pageable pageable) {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Convenio> query = builder.createQuery(Convenio.class);
        Root<Convenio> root = query.from(Convenio.class);

        query.orderBy(builder.asc(root.get("id")));
        Predicate[] predicato = this.adicionarFiltros(builder, convenioFiltro, root);
        query.where(predicato);

        TypedQuery<Convenio> tiped = this.entityManager.createQuery(query);
        this.adicionarPaginacao(tiped, pageable);

        return new PageImpl<>(tiped.getResultList(), pageable, this.total(convenioFiltro));
    }

    private Predicate[] adicionarFiltros(CriteriaBuilder builder, ConvenioFiltro convenioFiltro, Root<Convenio> root) {
        List<Predicate> lista = new ArrayList<>();

        if (!StringUtils.isEmpty(convenioFiltro.getNome()))
            lista.add(builder.like(builder.lower(root.get(Convenio_.NOME)), "%" + convenioFiltro.getNome().toLowerCase() + "%"));

        return lista.toArray(new Predicate[lista.size()]);
    }

    private Long total(ConvenioFiltro convenioFiltro) {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<Convenio> root = query.from(Convenio.class);

        Predicate[] predicato = adicionarFiltros(builder, convenioFiltro, root);
        query.where(predicato);
        query.select(builder.count(root));
        return this.entityManager.createQuery(query).getSingleResult();
    }
}
