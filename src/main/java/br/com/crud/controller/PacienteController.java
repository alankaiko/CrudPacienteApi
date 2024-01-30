package br.com.crud.controller;

import br.com.crud.acore.controller.AbstractController;
import br.com.crud.model.Paciente;
import br.com.crud.model.filter.PacienteFiltro;
import br.com.crud.service.PacienteService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping(value = PacienteController.PATH)
public class PacienteController extends AbstractController<Paciente, PacienteFiltro> {
    static final String PATH = "paciente";
    private final PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        super(pacienteService);
        this.pacienteService = pacienteService;
    }

    @GetMapping(params = "paginado")
    public Page<Paciente> paginado(PacienteFiltro pacienteFiltro, Pageable page) {
        return this.pacienteService.paginado(pacienteFiltro, page);
    }
}
