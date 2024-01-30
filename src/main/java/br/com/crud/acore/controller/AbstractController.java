package br.com.crud.acore.controller;

import br.com.crud.acore.model.AbstractFilter;
import br.com.crud.acore.model.AbstractModel;
import br.com.crud.acore.service.AbstractService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

public abstract class AbstractController<T extends AbstractModel, D extends AbstractFilter> {
    private final AbstractService<T, D> service;

    public AbstractController(AbstractService<T, D> service) {
        this.service = service;
    }

    @PostMapping
    public T salvar(@Valid @RequestBody T entidade) {
        return this.service.salvar(entidade);
    }

    @DeleteMapping({"{id}"})
    public void deletar(@PathVariable Long id) {
        this.service.deletar(id);
    }

    @GetMapping({"{id}"})
    public T buscarId(@PathVariable Long id) {
        return this.service.buscarId(id);
    }

    @GetMapping
    public List<T> listar() {
        return this.service.listar();
    }

    @PutMapping("/{id}")
    public ResponseEntity<T> atualizar(@PathVariable Long id, @Valid @RequestBody T entity) {
        T salvo = this.service.atualizar(id, entity);
        return ResponseEntity.ok(salvo);
    }

    @GetMapping("imprimir/{id}")
    public ResponseEntity<?> imprimir(@PathVariable Long id) {
        return this.service.imprimir(id);
    }
}
