package br.com.crud.acore.service;

import br.com.crud.acore.model.AbstractFilter;
import br.com.crud.acore.model.AbstractModel;

import java.util.List;

public interface AbstractService<T extends AbstractModel, D extends AbstractFilter> {
    T salvar(T entidade);

    void deletar(Long codigo);

    T buscarId(Long codigo);

    List<T> listar();
}
