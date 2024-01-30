package br.com.crud.controller;

import br.com.crud.acore.controller.AbstractController;
import br.com.crud.model.Assinador;
import br.com.crud.model.filter.AssinadorFiltro;
import br.com.crud.service.AssinadorService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping(value = AssinadorController.PATH)
public class AssinadorController extends AbstractController<Assinador, AssinadorFiltro> {
    static final String PATH = "assinador";
    private final AssinadorService assinadorService;

    public AssinadorController(AssinadorService assinadorService) {
        super(assinadorService);
        this.assinadorService = assinadorService;
    }

    @GetMapping(params = "paginado")
    public Page<Assinador> paginado(AssinadorFiltro assinadorFiltro, Pageable page) {
        return this.assinadorService.paginado(assinadorFiltro, page);
    }
}
