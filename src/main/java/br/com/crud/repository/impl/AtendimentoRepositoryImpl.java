package br.com.crud.repository.impl;

import br.com.crud.acore.repository.impl.AbstractRepositoryImpl;
import br.com.crud.model.*;
import br.com.crud.model.Assinador_;
import br.com.crud.model.Atendimento_;
import br.com.crud.model.Convenio_;
import br.com.crud.model.Hospital_;
import br.com.crud.model.Medico_;
import br.com.crud.model.Paciente_;
import br.com.crud.model.filter.AtendimentoFiltro;
import br.com.crud.repository.AtendimentoRepository;
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
public class AtendimentoRepositoryImpl extends AbstractRepositoryImpl<Atendimento, AtendimentoFiltro, Long> implements AtendimentoRepository {
    private final EntityManager entityManager;

    public AtendimentoRepositoryImpl(EntityManager entityManager) {
        super(Atendimento.class, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public Page<Atendimento> paginado(AtendimentoFiltro atendimentoFiltro, Pageable pageable) {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Atendimento> query = builder.createQuery(Atendimento.class);
        Root<Atendimento> root = query.from(Atendimento.class);

        query.orderBy(builder.asc(root.get("id")));
        Predicate[] predicato = this.adicionarFiltros(builder, atendimentoFiltro, root);
        query.where(predicato);

        TypedQuery<Atendimento> tiped = this.entityManager.createQuery(query);
        this.adicionarPaginacao(tiped, pageable);

        return new PageImpl<>(tiped.getResultList(), pageable, this.total(atendimentoFiltro));
    }

    private Predicate[] adicionarFiltros(CriteriaBuilder builder, AtendimentoFiltro atendimentoFiltro, Root<Atendimento> root) {
        List<Predicate> lista = new ArrayList<>();

        Join<Atendimento, Paciente> pacienteJoin = root.join(Atendimento_.PACIENTE);
        Join<Atendimento, Hospital> hospitalJoin = root.join(Atendimento_.HOSPITAL);
        Join<Atendimento, Medico> medicoJoin = root.join(Atendimento_.MEDICO);
        Join<Atendimento, Convenio> convenioJoin = root.join(Atendimento_.CONVENIO);
        Join<Atendimento, Assinador> assinadorJoin = root.join(Atendimento_.ASSINADOR);

        if (!StringUtils.isEmpty(atendimentoFiltro.getProcedimento()))
            lista.add(builder.like(builder.lower(root.get(Atendimento_.PROCEDIMENTO)), "%" + atendimentoFiltro.getProcedimento().toLowerCase() + "%"));

        if (!StringUtils.isEmpty(atendimentoFiltro.getNomePaciente()))
            lista.add(builder.like(builder.lower(pacienteJoin.get(Paciente_.NOME)), "%" + atendimentoFiltro.getNomePaciente().toLowerCase() + "%"));

        if (!StringUtils.isEmpty(atendimentoFiltro.getNomeHospital()))
            lista.add(builder.like(builder.lower(hospitalJoin.get(Hospital_.NOME)), "%" + atendimentoFiltro.getNomeHospital().toLowerCase() + "%"));

        if (!StringUtils.isEmpty(atendimentoFiltro.getNomeMedico()))
            lista.add(builder.like(builder.lower(medicoJoin.get(Medico_.NOME)), "%" + atendimentoFiltro.getNomeMedico().toLowerCase() + "%"));

        if (!StringUtils.isEmpty(atendimentoFiltro.getNomeConvenio()))
            lista.add(builder.like(builder.lower(convenioJoin.get(Convenio_.NOME)), "%" + atendimentoFiltro.getNomeConvenio().toLowerCase() + "%"));

        if (!StringUtils.isEmpty(atendimentoFiltro.getNomeAssinador()))
            lista.add(builder.like(builder.lower(assinadorJoin.get(Assinador_.NOME)), "%" + atendimentoFiltro.getNomeAssinador().toLowerCase() + "%"));

        return lista.toArray(new Predicate[lista.size()]);
    }

    private void adicionarPaginacao(TypedQuery<?> tiped, Pageable page) {
        int paginaatual = page.getPageNumber();
        int totalporpagina = page.getPageSize();
        int primeiroRegistroDaPagina = paginaatual * totalporpagina;

        tiped.setFirstResult(primeiroRegistroDaPagina);
        tiped.setMaxResults(totalporpagina);
    }

    private Long total(AtendimentoFiltro atendimentoFiltro) {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<Atendimento> root = query.from(Atendimento.class);

        Predicate[] predicato = adicionarFiltros(builder, atendimentoFiltro, root);
        query.where(predicato);
        query.select(builder.count(root));
        return this.entityManager.createQuery(query).getSingleResult();
    }
}
