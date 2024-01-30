package br.com.crud.repository.impl;

import br.com.crud.acore.repository.impl.AbstractRepositoryImpl;
import br.com.crud.model.Curso;
import br.com.crud.model.Curso_;
import br.com.crud.model.filter.CursoFiltro;
import br.com.crud.repository.CursoRepository;
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
public class CursoRepositoryImpl extends AbstractRepositoryImpl<Curso, CursoFiltro, Long> implements CursoRepository {
    private final EntityManager entityManager;

    public CursoRepositoryImpl(EntityManager entityManager) {
        super(Curso.class, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public Page<Curso> paginado(CursoFiltro cursoFiltro, Pageable pageable) {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Curso> query = builder.createQuery(Curso.class);
        Root<Curso> root = query.from(Curso.class);

        query.orderBy(builder.asc(root.get("id")));
        Predicate[] predicato = this.adicionarFiltros(builder, cursoFiltro, root);
        query.where(predicato);

        TypedQuery<Curso> tiped = this.entityManager.createQuery(query);
        this.adicionarPaginacao(tiped, pageable);

        return new PageImpl<>(tiped.getResultList(), pageable, this.total(cursoFiltro));
    }

    private Predicate[] adicionarFiltros(CriteriaBuilder builder, CursoFiltro cursoFiltro, Root<Curso> root) {
        List<Predicate> lista = new ArrayList<>();

        if (!StringUtils.isEmpty(cursoFiltro.getTitulo()))
            lista.add(builder.like(builder.lower(root.get(Curso_.titulo)), "%" + cursoFiltro.getTitulo().toLowerCase() + "%"));

        return lista.toArray(new Predicate[lista.size()]);
    }

    private Long total(CursoFiltro cursoFiltro) {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<Curso> root = query.from(Curso.class);

        Predicate[] predicato = adicionarFiltros(builder, cursoFiltro, root);
        query.where(predicato);
        query.select(builder.count(root));
        return this.entityManager.createQuery(query).getSingleResult();
    }
}
