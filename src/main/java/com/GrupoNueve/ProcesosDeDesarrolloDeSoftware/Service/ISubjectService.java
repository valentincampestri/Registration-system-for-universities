package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Service;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.Response.MessageResponseDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.SubjectDto;

import java.util.List;

public interface ISubjectService {
    List<SubjectDto> getAllSubjects();
    MessageResponseDto createSubject(SubjectDto subjectDto);
}
