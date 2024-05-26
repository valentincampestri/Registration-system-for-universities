package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.service.implementation.strategies;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.entity.Course;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.entity.Inscription;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.entity.Student;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.entity.Subject;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.exception.BadRequestException;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.service.IInscriptionCheckStrategy;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AlreadyApprovedSubjectCheckStrategy implements IInscriptionCheckStrategy {
    @Override
    public void check(Inscription inscription) {
        boolean okPrerequisites;
        Set<String> necessaryPrerequisites = new HashSet<>();
        Set<String> approvedSubjects = getApprovedSubjects(inscription);
        List<Course> courses = inscription.getCourses();
        Subject subject;
        if (!courses.isEmpty()) {
            for (Course course : courses) {
                subject = course.getSubject();
                if (approvedSubjects.contains(subject.getSubjectCode())) {
                    throw new BadRequestException("You are already approved in one or more subjects that you are trying to enroll in this term.");
                }
            }
        }
    }

    private static Set<String> getApprovedSubjects(Inscription inscription) {
        Set<String> approvedSubjects = new HashSet<>();
        Student student = inscription.getStudent();
        if (student != null && student.getCareer() != null) {
            //prerequisites
            if (!student.getApprovedSubjectList().isEmpty()) {
                for (Subject subject : student.getApprovedSubjectList()) {
                    if (subject.getSubjectCode() != null) {
                        approvedSubjects.add(subject.getSubjectCode());
                    }
                }
            }
        }
        return approvedSubjects;
    }
}

