package br.com.crud.repository.impl;

import br.com.crud.acore.repository.impl.AbstractRepositoryImpl;
import br.com.crud.model.*;
import br.com.crud.model.Assinador_;
import br.com.crud.model.Atendimento_;
import br.com.crud.model.Convenio_;
import br.com.crud.model.Hospital_;
import br.com.crud.model.Medico_;
import br.com.crud.model.Paciente_;
import br.com.crud.model.filter.AtendimentoExcluidoFiltro;
import br.com.crud.repository.AtendimentoExcluidoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class AtendimentoExcluidoRepositoryImpl extends AbstractRepositoryImpl<AtendimentoExcluido, AtendimentoExcluidoFiltro, Long> implements AtendimentoExcluidoRepository {
    private final EntityManager entityManager;

    public AtendimentoExcluidoRepositoryImpl(EntityManager entityManager) {
        super(AtendimentoExcluido.class, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public Page<AtendimentoExcluido> paginado(AtendimentoExcluidoFiltro atendimentoExcluidoFiltro, Pageable pageable) {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<AtendimentoExcluido> query = builder.createQuery(AtendimentoExcluido.class);
        Root<AtendimentoExcluido> root = query.from(AtendimentoExcluido.class);

        query.orderBy(builder.asc(root.get("id")));
        Predicate[] predicato = this.adicionarFiltros(builder, atendimentoExcluidoFiltro, root);
        query.where(predicato);

        TypedQuery<AtendimentoExcluido> tiped = this.entityManager.createQuery(query);
        this.adicionarPaginacao(tiped, pageable);

        return new PageImpl<>(tiped.getResultList(), pageable, this.total(atendimentoExcluidoFiltro));
    }

    private Predicate[] adicionarFiltros(CriteriaBuilder builder, AtendimentoExcluidoFiltro atendimentoExcluidoFiltro, Root<AtendimentoExcluido> root) {
        List<Predicate> lista = new ArrayList<>();

        Join<AtendimentoExcluido, Paciente> pacienteJoin = root.join(Atendimento_.PACIENTE);
        Join<AtendimentoExcluido, Hospital> hospitalJoin = root.join(Atendimento_.HOSPITAL);
        Join<AtendimentoExcluido, Medico> medicoJoin = root.join(Atendimento_.MEDICO);
        Join<AtendimentoExcluido, Convenio> convenioJoin = root.join(Atendimento_.CONVENIO);
        Join<AtendimentoExcluido, Assinador> assinadorJoin = root.join(Atendimento_.ASSINADOR);

        if (!StringUtils.isEmpty(atendimentoExcluidoFiltro.getProcedimento()))
            lista.add(builder.like(builder.lower(root.get(Atendimento_.PROCEDIMENTO)), "%" + atendimentoExcluidoFiltro.getProcedimento().toLowerCase() + "%"));

        if (!StringUtils.isEmpty(atendimentoExcluidoFiltro.getNomePaciente()))
            lista.add(builder.like(builder.lower(pacienteJoin.get(Paciente_.NOME)), "%" + atendimentoExcluidoFiltro.getNomePaciente().toLowerCase() + "%"));

        if (!StringUtils.isEmpty(atendimentoExcluidoFiltro.getNomeHospital()))
            lista.add(builder.like(builder.lower(hospitalJoin.get(Hospital_.NOME)), "%" + atendimentoExcluidoFiltro.getNomeHospital().toLowerCase() + "%"));

        if (!StringUtils.isEmpty(atendimentoExcluidoFiltro.getNomeMedico()))
            lista.add(builder.like(builder.lower(medicoJoin.get(Medico_.NOME)), "%" + atendimentoExcluidoFiltro.getNomeMedico().toLowerCase() + "%"));

        if (!StringUtils.isEmpty(atendimentoExcluidoFiltro.getNomeConvenio()))
            lista.add(builder.like(builder.lower(convenioJoin.get(Convenio_.NOME)), "%" + atendimentoExcluidoFiltro.getNomeConvenio().toLowerCase() + "%"));

        if (!StringUtils.isEmpty(atendimentoExcluidoFiltro.getNomeAssinador()))
            lista.add(builder.like(builder.lower(assinadorJoin.get(Assinador_.NOME)), "%" + atendimentoExcluidoFiltro.getNomeAssinador().toLowerCase() + "%"));

        return lista.toArray(new Predicate[lista.size()]);
    }

    private void adicionarPaginacao(TypedQuery<?> tiped, Pageable page) {
        int paginaatual = page.getPageNumber();
        int totalporpagina = page.getPageSize();
        int primeiroRegistroDaPagina = paginaatual * totalporpagina;

        tiped.setFirstResult(primeiroRegistroDaPagina);
        tiped.setMaxResults(totalporpagina);
    }

    private Long total(AtendimentoExcluidoFiltro atendimentoExcluidoFiltro) {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<AtendimentoExcluido> root = query.from(AtendimentoExcluido.class);

        Predicate[] predicato = adicionarFiltros(builder, atendimentoExcluidoFiltro, root);
        query.where(predicato);
        query.select(builder.count(root));
        return this.entityManager.createQuery(query).getSingleResult();
    }
}
