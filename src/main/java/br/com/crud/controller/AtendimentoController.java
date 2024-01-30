package br.com.crud.controller;

import br.com.crud.acore.controller.AbstractController;
import br.com.crud.model.Atendimento;
import br.com.crud.model.filter.AtendimentoFiltro;
import br.com.crud.service.AtendimentoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping(value = AtendimentoController.PATH)
public class AtendimentoController extends AbstractController<Atendimento, AtendimentoFiltro> {
    static final String PATH = "atendimento";
    private final AtendimentoService atendimentoService;

    public AtendimentoController(AtendimentoService atendimentoService) {
        super(atendimentoService);
        this.atendimentoService = atendimentoService;
    }

    @GetMapping(params = "paginado")
    public Page<Atendimento> paginado(AtendimentoFiltro atendimentoFiltro, Pageable page) {
        return this.atendimentoService.paginado(atendimentoFiltro, page);
    }
}
