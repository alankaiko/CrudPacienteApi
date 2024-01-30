package br.com.crud.controller;

import br.com.crud.acore.controller.AbstractController;
import br.com.crud.model.AtendimentoExcluido;
import br.com.crud.model.filter.AtendimentoExcluidoFiltro;
import br.com.crud.service.AtendimentoExcluidoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping(value = AtendimentoExcluidoController.PATH)
public class AtendimentoExcluidoController extends AbstractController<AtendimentoExcluido, AtendimentoExcluidoFiltro> {
    static final String PATH = "atendimentoExcluido";
    private final AtendimentoExcluidoService atendimentoExcluidoService;

    public AtendimentoExcluidoController(AtendimentoExcluidoService atendimentoExcluidoService) {
        super(atendimentoExcluidoService);
        this.atendimentoExcluidoService = atendimentoExcluidoService;
    }

    @GetMapping(params = "paginado")
    public Page<AtendimentoExcluido> paginado(AtendimentoExcluidoFiltro atendimentoExcluidoFiltro, Pageable page) {
        return this.atendimentoExcluidoService.paginado(atendimentoExcluidoFiltro, page);
    }
}
