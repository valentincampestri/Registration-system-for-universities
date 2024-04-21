package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Service.Implementation;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity.Subject;
import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)

public class SubjectService {
    Graph<Subject, DefaultEdge> subjectGraph;

    public SubjectService() {
        subjectGraph = new DefaultDirectedGraph<>(DefaultEdge.class);
    }

    public void addSubject(Subject subject) {
        subjectGraph.addVertex(subject);
    }

    public void addPrerequisite(Subject subject, Subject prerequisite) {
        subjectGraph.addEdge(subject, prerequisite);
    }

    public List<Subject> getPrerequisites(Subject subject) {
        return Graphs.successorListOf(subjectGraph, subject);
    }
}
