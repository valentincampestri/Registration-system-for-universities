package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.service.implementation.strategies;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.entity.Course;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.entity.Inscription;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.exception.BadRequestException;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.repository.IInscriptionRepository;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.service.IInscriptionCheckStrategy;

import java.util.List;

public class AlreadyEnrolledCheckStrategy implements IInscriptionCheckStrategy {
    IInscriptionRepository inscriptionRepository;

    public AlreadyEnrolledCheckStrategy(IInscriptionRepository inscriptionRepository) {
        this.inscriptionRepository = inscriptionRepository;
    }


    @Override
    public void check(Inscription inscription) {
        List<Inscription> existentInscriptions = inscriptionRepository.getInscriptionByStudentCode(inscription.getStudent().getPersonCode());

        List<Course> courses = inscription.getCourses();
        if (!courses.isEmpty()) {
            for (Course course : courses) {
                for (Inscription eachInscription : existentInscriptions) {
                    for (Course eachCourseDataBase : eachInscription.getCourses()) {
                        if (eachCourseDataBase.getSubject().getSubjectCode().equals(course.getSubject().getSubjectCode())
                                && eachCourseDataBase.getTerm().equals(course.getTerm())) {
                            throw new BadRequestException("You are already enrolled in one or more subjects that you are trying to enroll in this term.");


                        }
                    }
                }
            }
        }
    }
}
