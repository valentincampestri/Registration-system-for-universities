package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Repository;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity.Professor;

import java.util.List;
import java.util.Optional;

public interface IProfessorRepository {
    void addProfessor(Professor professor);

    void deleteProfessor(String professorId);

    void updateProfessor(Professor professor);

    Optional<Professor> getProfessorById(String professorId);

    List<Professor> getAllProfessors();
}
