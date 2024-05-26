package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.service;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.dto.response.MessageResponseDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.dto.SubjectDto;

import java.util.List;

public interface ISubjectService {
    List<SubjectDto> getAllSubjects();
    MessageResponseDto createSubject(SubjectDto subjectDto);
}
