package br.com.crud.controller;

import br.com.crud.acore.controller.AbstractController;
import br.com.crud.model.Curso;
import br.com.crud.model.filter.CursoFiltro;
import br.com.crud.service.CursoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping(value = CursoController.PATH)
public class CursoController extends AbstractController<Curso, CursoFiltro> {
    static final String PATH = "curso";
    private final CursoService cursoService;

    public CursoController(CursoService cursoService) {
        super(cursoService);
        this.cursoService = cursoService;
    }

    @GetMapping(params = "paginado")
    public Page<Curso> paginado(CursoFiltro cursoFiltro, Pageable page) {
        return this.cursoService.paginado(cursoFiltro, page);
    }
}
