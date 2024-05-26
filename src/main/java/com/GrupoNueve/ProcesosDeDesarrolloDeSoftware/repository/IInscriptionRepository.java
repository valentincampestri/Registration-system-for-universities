package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.repository;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.entity.Inscription;

import java.util.List;

public interface IInscriptionRepository {
    void addInscription(Inscription inscription);

    void deleteInscription(String inscriptionCode);

    void updateInscription(Inscription inscription);

    List<Inscription> getInscriptionByStudentCode(String studentCode);

    List<Inscription> getAllInscriptions();

    List<Inscription> getInscriptionsByCourseCode(String courseCode);
}
