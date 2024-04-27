package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Repository.Implementation;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity.Professor;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Repository.IProfessorRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProfessorRepository implements IProfessorRepository {
    private List<Professor> professorsList = new ArrayList<>();

    @Override
    public void addProfessor(Professor professor) {
        professorsList.add(professor);
    }

    @Override
    public void deleteProfessor(String professorId) {
        professorsList.removeIf(professor -> professor.getPersonCode().equals(professorId));
    }

    @Override
    public void updateProfessor(Professor professor) {
        professorsList.remove(professor);
        professorsList.add(professor);
    }

    @Override
    public Optional<Professor> getProfessorByCode(String professorId) {
        return professorsList.stream()
                .filter(professor -> professor.getPersonCode().equals(professorId))
                .findFirst();
    }

    @Override
    public List<Professor> getAllProfessors() {
        return professorsList;
    }
}
