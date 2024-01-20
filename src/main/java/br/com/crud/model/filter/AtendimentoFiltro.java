package br.com.crud.model.filter;

import br.com.crud.acore.model.AbstractFilter;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AtendimentoFiltro extends AbstractFilter {
    private String nome;
    private String procedimento;
    private String nomeHospital;
    private String nomeMedico;
    private String nomeConvenio;
    private String nomeAssinador;
}
