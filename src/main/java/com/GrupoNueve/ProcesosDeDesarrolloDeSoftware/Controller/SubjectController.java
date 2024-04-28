package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Controller;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.Request.CourseRequestDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.Response.MessageResponseDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.SubjectDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Repository.ISubjectRepository;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Service.Implementation.SubjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
