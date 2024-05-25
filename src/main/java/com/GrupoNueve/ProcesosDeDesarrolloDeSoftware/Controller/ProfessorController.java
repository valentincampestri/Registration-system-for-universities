package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Controller;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.Request.ProfessorRequestDto;
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

    @PostMapping("/create")
    public ResponseEntity<?> createProfessor(@RequestBody ProfessorRequestDto professorRequestDto){
        return ResponseEntity.ok(professorService.createProfessor(professorRequestDto));
    }

    @GetMapping("/workload/{professorCode}")
    public ResponseEntity<?> calculateMonthlyWorkload(@PathVariable String professorCode){
        return ResponseEntity.ok(professorService.calculateMonthlyWorkload(professorCode));
    }

}
