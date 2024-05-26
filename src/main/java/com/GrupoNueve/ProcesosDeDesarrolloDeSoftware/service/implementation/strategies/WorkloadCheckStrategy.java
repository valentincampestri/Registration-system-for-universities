package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.service.implementation.strategies;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.entity.*;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.exception.BadRequestException;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.repository.IInscriptionRepository;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.service.IInscriptionCheckStrategy;

import java.util.List;

public class WorkloadCheckStrategy implements IInscriptionCheckStrategy {
    @Override
    public void check(Inscription inscription) {
        Student student = inscription.getStudent();
        Career career;
        int maxWorkLoad = 0;
        if (student != null && student.getCareer() != null) {
            //workload
            career = student.getCareer();
            if (career.getMaxWorkload() != null) maxWorkLoad = career.getMaxWorkload();
        }
        //maxWorkLoad retrieved

        int currentWorkLoad = 0;
        List<Course> courses = inscription.getCourses();
        if (!courses.isEmpty()) {
            for (Course course : courses) {
                Subject subject = course.getSubject();
                if (subject != null && subject.getWorkload() != null) currentWorkLoad += subject.getWorkload();
            }
        }
        if (currentWorkLoad > maxWorkLoad) {
            throw new BadRequestException("The workload exceeds the maximum workload allowed for the career.");
        }

    }
}


