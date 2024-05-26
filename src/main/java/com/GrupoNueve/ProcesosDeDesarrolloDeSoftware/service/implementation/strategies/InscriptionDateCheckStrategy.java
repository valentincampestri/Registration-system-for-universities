package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.service.implementation.strategies;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.entity.Course;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.entity.Inscription;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.exception.BadRequestException;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.service.IInscriptionCheckStrategy;

import java.time.LocalDate;
import java.util.List;

public class InscriptionDateCheckStrategy implements IInscriptionCheckStrategy {
    @Override
    public void check(Inscription inscription) {
        List<Course> courses = inscription.getCourses();
        LocalDate lastInscriptionDate;
        LocalDate today = LocalDate.now();
        if (!courses.isEmpty()) {
            for (Course course : courses) {
                lastInscriptionDate = course.getLastInscriptionDate();
                if (lastInscriptionDate != null && today.isAfter(lastInscriptionDate)) {
                    throw new BadRequestException("The inscription date has expired for one or more subjects that you are trying to enroll in this term.");
                }
            }
        }

    }
}
