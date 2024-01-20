package br.com.crud.acore.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class AbstractFilter implements Serializable {
    private int pagina;
    private int itensPorPagina;
}
