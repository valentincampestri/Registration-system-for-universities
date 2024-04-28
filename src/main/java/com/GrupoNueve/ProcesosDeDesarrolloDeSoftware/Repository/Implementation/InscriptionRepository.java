package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Repository.Implementation;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity.Inscription;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Repository.IInscriptionRepository;
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

import static java.util.stream.Collectors.toList;

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

    private void loadInscriptions() throws IOException {
        File file;
        ObjectMapper objectMapper = new ObjectMapper();
        List<Inscription> mappedInscriptions;

        try {
            file = ResourceUtils.getFile("classpath:Inscription.json");

            objectMapper.registerModule(new JavaTimeModule());
            objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            objectMapper.setDateFormat(new SimpleDateFormat("dd-MM-yyyy"));
            mappedInscriptions = objectMapper.readValue(file, new TypeReference<List<Inscription>>() {
            });
            inscriptionsList = mappedInscriptions;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println(LocalDate.now());
        }
    }
}
