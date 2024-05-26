package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.service.implementation.strategies;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.entity.*;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.exception.BadRequestException;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.service.IInscriptionCheckStrategy;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PrerequisitesCheckStrategy implements IInscriptionCheckStrategy {

    @Override
    public void check(Inscription inscription) {
        boolean okPrerequisites;
        Set<String> necessaryPrerequisites = new HashSet<>();
        Set<String> approvedSubjects = getApprovedSubjects(inscription);
        List<Course> courses = inscription.getCourses();
        Subject subject;
        if (!courses.isEmpty()) {
            for (Course course : courses) {
                //prerequisites
                subject = course.getSubject();
                if (!subject.getPrerequisitesCodeList().isEmpty()) {
                    necessaryPrerequisites.addAll(subject.getPrerequisitesCodeList());
                }
            }
            okPrerequisites = approvedSubjects.containsAll(necessaryPrerequisites);
            if (!okPrerequisites) {
                throw new BadRequestException("You need to pass one or more subjects as a prerequisite to enroll in one or more subjects that you are trying to enroll in this term.");
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
