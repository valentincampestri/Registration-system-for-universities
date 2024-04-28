package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Repository.Implementation;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity.Professor;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity.Professor;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Repository.IProfessorRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProfessorRepository implements IProfessorRepository {
    private List<Professor> professorsList = new ArrayList<>();

    /*public ProfessorRepository() throws IOException {
        loadProfessors();
    }*/

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

    /*private void loadProfessors() throws IOException {
        File file;
        ObjectMapper objectMapper = new ObjectMapper();
        List<Professor> mappedProfessors;

        try {
            file = ResourceUtils.getFile("classpath:Professor.json");

            objectMapper.registerModule(new JavaTimeModule());
            objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            objectMapper.setDateFormat(new SimpleDateFormat("dd-MM-yyyy"));
            mappedProfessors = objectMapper.readValue(file, new TypeReference<List<Professor>>() {
            });
            professorsList = mappedProfessors;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println(LocalDate.now());
        }
    }*/
}
