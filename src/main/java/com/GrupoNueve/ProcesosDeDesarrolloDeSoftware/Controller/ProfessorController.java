package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Controller;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Service.IProfessorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/professor")
public class ProfessorController {
    IProfessorService professorService;

    public ProfessorController(IProfessorService professorService) {
        this.professorService = professorService;
    }

    @PostMapping("/createProfessor")
    public ResponseEntity<?> createProfessor(@RequestBody ProfessorRequestDto professorRequestDto){
        return ResponseEntity.ok(professorService.createProfessor(professorRequestDto));
    }


}
