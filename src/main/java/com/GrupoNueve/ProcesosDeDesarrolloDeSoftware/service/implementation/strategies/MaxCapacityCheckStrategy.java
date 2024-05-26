package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.service.implementation.strategies;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.entity.Course;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.entity.Inscription;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.exception.BadRequestException;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.repository.IInscriptionRepository;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.service.IInscriptionCheckStrategy;

public class MaxCapacityCheckStrategy implements IInscriptionCheckStrategy {
    IInscriptionRepository inscriptionRepository;

    public MaxCapacityCheckStrategy(IInscriptionRepository inscriptionRepository) {
        this.inscriptionRepository = inscriptionRepository;
    }

    @Override
    public void check(Inscription inscription) {
        for (Course course : inscription.getCourses()) {
            if (course.getClassroom().getMaxCapacity() <= inscriptionRepository.getInscriptionsByCourseCode(course.getCourseCode()).size()) {
                throw new BadRequestException("The course " + course.getCourseCode() + " is already full.");
            }
        }
    }
}
