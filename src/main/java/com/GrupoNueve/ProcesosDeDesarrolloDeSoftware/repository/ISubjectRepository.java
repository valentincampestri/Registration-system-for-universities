package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.repository;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.entity.Subject;

import java.util.List;
import java.util.Optional;

public interface ISubjectRepository {
    void addSubject(Subject subject);

    void deleteSubject(String subjectCode);

    void updateSubject(Subject subject);

    Optional<Subject> getSubjectByCode(String subjectCode);

    List<Subject> getAllSubject();
}