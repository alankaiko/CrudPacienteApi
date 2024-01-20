package br.com.crud.acore.service;

import br.com.crud.acore.model.AbstractDTO;
import br.com.crud.acore.model.AbstractEntity;

import java.util.List;

public interface AbstractService<T extends AbstractEntity, D extends AbstractDTO> {
    T salvar(T entidade);

    void deletar(Long codigo);

    T buscarId(Long codigo);

    List<T> listar();
}
