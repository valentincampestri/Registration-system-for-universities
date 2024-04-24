package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Repository;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity.Subject;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

public interface ISubjectRepository {
    public Graph<Subject, DefaultEdge> getAllSubjects();
}
