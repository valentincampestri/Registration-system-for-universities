package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Controller;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Repository.IProfessorRepository;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Service.IProfessorService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/professor")
public class ProfessorController {
    IProfessorService professorService;

    public ProfessorController(IProfessorService professorService) {
        this.professorService = professorService;
    }
}
