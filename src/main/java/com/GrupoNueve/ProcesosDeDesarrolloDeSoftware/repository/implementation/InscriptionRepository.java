package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.repository.implementation;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.entity.Inscription;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.repository.IInscriptionRepository;
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

@Repository
public class InscriptionRepository implements IInscriptionRepository {

    private List<Inscription> inscriptionsList = new ArrayList<>();

    public InscriptionRepository() throws IOException {
        loadInscriptions();
    }

    @Override
    public void addInscription(Inscription inscription) {
        inscriptionsList.add(inscription);
    }

    @Override
    public void deleteInscription(String inscriptionCode) {
        inscriptionsList.removeIf(inscription -> inscription.getInscriptionCode().equals(inscriptionCode));
    }

    @Override
    public void updateInscription(Inscription inscription) {
        inscriptionsList.remove(inscription);
        inscriptionsList.add(inscription);
    }

    @Override
    public List<Inscription> getInscriptionByStudentCode(String studentCode) {
        return inscriptionsList.stream()
                .filter(inscription -> inscription.getStudent().getPersonCode().equals(studentCode))
                .toList();
    }

    @Override
    public List<Inscription> getAllInscriptions() {
        return inscriptionsList;
    }

    @Override
    public List<Inscription> getInscriptionsByCourseCode(String courseCode) {
        return inscriptionsList.stream()
                .filter(inscription -> inscription.getCourses().stream().anyMatch(course -> course.getCourseCode().equals(courseCode)))
                .toList();
    }

    private void loadInscriptions() {
        File file;
        ObjectMapper objectMapper = new ObjectMapper();
        List<Inscription> mappedInscriptions;

        try {
            file = ResourceUtils.getFile("classpath:Inscription.json");

            objectMapper.registerModule(new JavaTimeModule());
            objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            objectMapper.setDateFormat(new SimpleDateFormat("dd-MM-yyyy"));
            mappedInscriptions = objectMapper.readValue(file, new TypeReference<>() {
            });
            inscriptionsList = mappedInscriptions;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println(LocalDate.now());
        }
    }
}
