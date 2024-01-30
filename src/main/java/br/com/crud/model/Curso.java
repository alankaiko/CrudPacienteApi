package br.com.crud.model;

import br.com.crud.acore.model.AbstractModel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class Curso extends AbstractModel {
    private String titulo;
    private String descricao;
}