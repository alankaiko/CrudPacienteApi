package br.com.crud.controller;

import br.com.crud.acore.resources.AbstractController;
import br.com.crud.model.Hospital;
import br.com.crud.model.filter.HospitalFiltro;
import br.com.crud.service.HospitalService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping(value = HospitalController.PATH)
public class HospitalController extends AbstractController<Hospital, HospitalFiltro> {
    static final String PATH = "hospital";
    private final HospitalService hospitalService;

    public HospitalController(HospitalService hospitalService) {
        super(hospitalService);
        this.hospitalService = hospitalService;
    }

    @GetMapping(params = "paginado")
    public Page<Hospital> paginado(HospitalFiltro hospitalFiltro, Pageable page) {
        return this.hospitalService.paginado(hospitalFiltro, page);
    }
}
