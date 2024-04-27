package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Repository.Implementation;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity.Subject;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Repository.ISubjectRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class SubjectRepository implements ISubjectRepository {

    private List<Subject> subjectsList = new ArrayList<>();

    @Override
    public void addSubject(Subject subject) {
        subjectsList.add(subject);
    }

    @Override
    public void deleteSubject(String subjectId) {
        subjectsList.removeIf(subject -> subject.getSubjectID().equals(subjectId));
    }

    @Override
    public void updateSubject(Subject subject) {
        subjectsList.remove(subject);
        subjectsList.add(subject);
    }

    @Override
    public Optional<Subject> getSubjectById(String subjectId) {
        return subjectsList.stream()
                .filter(subject -> subject.getSubjectID().equals(subjectId))
                .findFirst();
    }

    @Override
    public List<Subject> getAllSubject() {
        return subjectsList;
    }
}
