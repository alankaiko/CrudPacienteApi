package br.com.crud.model.filter;

import br.com.crud.acore.model.AbstractFilter;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PacienteFiltro extends AbstractFilter {
    private String nome;
}
