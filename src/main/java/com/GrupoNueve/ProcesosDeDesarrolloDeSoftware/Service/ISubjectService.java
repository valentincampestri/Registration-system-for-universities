package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Service;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.SubjectDto;

import java.util.List;

public interface ISubjectService {

    public void addPrerequisites(String subjectId, String prerequisites);
    public List<SubjectDto> getPrerequisites(String subjectId);

}
