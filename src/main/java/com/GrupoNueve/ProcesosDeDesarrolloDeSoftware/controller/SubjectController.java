package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.controller;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.dto.SubjectDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.dto.response.MessageResponseDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.repository.ISubjectRepository;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.service.implementation.SubjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/subject")
public class SubjectController {
    private final SubjectService subjectService;
    ISubjectRepository subjectRepository;

    public SubjectController(ISubjectRepository subjectRepository, SubjectService subjectService) {
        this.subjectRepository = subjectRepository;
        this.subjectService = subjectService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<SubjectDto>> getAllSubjects() {
        return ResponseEntity.ok(subjectService.getAllSubjects());
    }

    @PostMapping("/create/{personId}/{subjectCode}")
    public ResponseEntity<MessageResponseDto> createSubject(@RequestBody SubjectDto subjectDto) {
        return ResponseEntity.ok(subjectService.createSubject(subjectDto));
    }
}
