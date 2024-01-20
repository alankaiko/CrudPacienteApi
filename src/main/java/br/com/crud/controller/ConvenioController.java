package br.com.crud.controller;

import br.com.crud.acore.resources.AbstractController;
import br.com.crud.model.Convenio;
import br.com.crud.model.filter.ConvenioFiltro;
import br.com.crud.service.ConvenioService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping(value = ConvenioController.PATH)
public class ConvenioController extends AbstractController<Convenio, ConvenioFiltro> {
    static final String PATH = "convenio";
    private final ConvenioService convenioService;

    public ConvenioController(ConvenioService convenioService) {
        super(convenioService);
        this.convenioService = convenioService;
    }

    @GetMapping(params = "paginado")
    public Page<Convenio> paginado(ConvenioFiltro convenioFiltro, Pageable page) {
        return this.convenioService.paginado(convenioFiltro, page);
    }
}
