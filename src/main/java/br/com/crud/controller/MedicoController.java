package br.com.crud.controller;

import br.com.crud.acore.controller.AbstractController;
import br.com.crud.model.Medico;
import br.com.crud.model.filter.MedicoFiltro;
import br.com.crud.service.MedicoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping(value = MedicoController.PATH)
public class MedicoController extends AbstractController<Medico, MedicoFiltro> {
    static final String PATH = "medico";
    private final MedicoService medicoService;

    public MedicoController(MedicoService medicoService) {
        super(medicoService);
        this.medicoService = medicoService;
    }

    @GetMapping(params = "paginado")
    public Page<Medico> paginado(MedicoFiltro medicoFiltro, Pageable page) {
        return this.medicoService.paginado(medicoFiltro, page);
    }
}
