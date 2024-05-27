package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.repository.implementation;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.entity.Subject;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.repository.ISubjectRepository;
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
public class SubjectRepository implements ISubjectRepository {

    private List<Subject> subjectsList = new ArrayList<>();

    public SubjectRepository() throws IOException {
        loadSubjects();
    }

    @Override
    public void addSubject(Subject subject) {
        subjectsList.add(subject);
    }

    @Override
    public void deleteSubject(String subjectCode) {
        subjectsList.removeIf(subject -> subject.getSubjectCode().equals(subjectCode));
    }

    @Override
    public void updateSubject(Subject subject) {
        subjectsList.remove(subject);
        subjectsList.add(subject);
    }

    @Override
    public Optional<Subject> getSubjectByCode(String subjectCode) {
        return subjectsList.stream()
                .filter(subject -> subject.getSubjectCode().equals(subjectCode))
                .findFirst();
    }

    @Override
    public List<Subject> getAllSubject() {
        return subjectsList;
    }

    private void loadSubjects() {
        File file;
        ObjectMapper objectMapper = new ObjectMapper();
        List<Subject> mappedSubjects;

        try {
            file = ResourceUtils.getFile("classpath:Subject.json");

            objectMapper.registerModule(new JavaTimeModule());
            objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            objectMapper.setDateFormat(new SimpleDateFormat("dd-MM-yyyy"));
            mappedSubjects = objectMapper.readValue(file, new TypeReference<>() {
            });
            subjectsList = mappedSubjects;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println(LocalDate.now());
        }
    }
}
