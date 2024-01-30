package br.com.crud.acore.service;

import br.com.crud.acore.model.AbstractFilter;
import br.com.crud.acore.model.AbstractModel;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AbstractService<T extends AbstractModel, D extends AbstractFilter> {
    T salvar(T entidade);

    void deletar(Long id);

    T buscarId(Long id);

    List<T> listar();

    T atualizar(Long id, T entity);

    ResponseEntity<?> imprimir(Long id);
}
