package br.com.crud.acore.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class AbstractDTO implements Serializable {
    private int pagina;
    private int itensPorPagina;
}
