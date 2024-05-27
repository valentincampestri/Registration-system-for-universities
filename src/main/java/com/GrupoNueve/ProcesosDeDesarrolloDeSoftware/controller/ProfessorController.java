package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.controller;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.dto.request.ProfessorRequestDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.service.IProfessorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/professor")
public class ProfessorController {
    IProfessorService professorService;

    public ProfessorController(IProfessorService professorService) {
        this.professorService = professorService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createProfessor(@RequestBody ProfessorRequestDto professorRequestDto) {
        return ResponseEntity.ok(professorService.createProfessor(professorRequestDto));
    }

    @GetMapping("/workload/{professorCode}")
    public ResponseEntity<?> calculateMonthlyWorkload(@PathVariable String professorCode) {
        return ResponseEntity.ok(professorService.calculateMonthlyWorkload(professorCode));
    }

}
