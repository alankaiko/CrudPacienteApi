package br.com.crud.acore.service.impl;

import br.com.crud.acore.model.AbstractFilter;
import br.com.crud.acore.model.AbstractModel;
import br.com.crud.acore.repository.AbstractRepository;
import br.com.crud.acore.service.AbstractService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public abstract class AbstractServiceImpl<T extends AbstractModel, D extends AbstractFilter> implements AbstractService<T, D> {
    protected AbstractRepository<T, D, Long> dao;

    public AbstractServiceImpl(AbstractRepository<T, D, Long> dao) {
        this.dao = dao;
    }

    @Override
    public T salvar(T entity) {
        return this.dao.save(entity);
    }

    @Override
    public void deletar(Long id) {
        this.dao.deleteById(id);
    }

    @Override
    public T buscarId(Long id) {
        return this.dao.findById(id).orElse((T) null);
    }

    @Override
    public List<T> listar() {
        return this.dao.findAll();
    }

    @Override
    public T atualizar(Long id, T entity) {
        try {
            T salvo = this.buscarId(id);
            BeanUtils.copyProperties(entity, salvo, "id");

            return this.salvar(salvo);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<?> imprimir(Long id) {
        return null;
    }
}
