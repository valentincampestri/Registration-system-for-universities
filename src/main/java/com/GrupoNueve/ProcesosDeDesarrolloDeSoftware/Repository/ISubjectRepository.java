package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Repository;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity.Course;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity.Subject;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

import java.util.List;
import java.util.Optional;

public interface ISubjectRepository {
    void addSubject(Subject subject);
    void deleteSubject(Integer subjectId);
    void updateSubject(Subject subject);
    Optional<Subject> getSubjectById(Integer subjectId);
    List<Subject> getAllSubject();
}