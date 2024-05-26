package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.repository;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.entity.Professor;

import java.util.List;
import java.util.Optional;

public interface IProfessorRepository {
    void addProfessor(Professor professor);

    void deleteProfessor(String professorCode);

    void updateProfessor(Professor professor);

    Optional<Professor> getProfessorByCode(String professorCode);

    List<Professor> getAllProfessors();
}
