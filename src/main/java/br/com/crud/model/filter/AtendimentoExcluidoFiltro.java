package br.com.crud.model.filter;

import br.com.crud.acore.model.AbstractFilter;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
public class AtendimentoExcluidoFiltro extends AbstractFilter {
    private String procedimento;
    private String nomePaciente;
    private String nomeHospital;
    private String nomeMedico;
    private String nomeConvenio;
    private String nomeAssinador;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataExclusaoInicial;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataExclusaoFinal;
}
