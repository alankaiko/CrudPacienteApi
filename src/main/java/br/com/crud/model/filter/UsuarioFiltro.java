package br.com.crud.model.filter;

import br.com.crud.acore.model.AbstractFilter;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioFiltro extends AbstractFilter {
    private String login;
    private String cpf;
}
