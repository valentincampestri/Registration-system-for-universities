package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Repository;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity.Inscription;

import java.util.List;
import java.util.Optional;

public interface IInscriptionRepository {
    void addInscription(Inscription inscription);

    void deleteInscription(String inscriptionCode);

    void updateInscription(Inscription inscription);

    List<Inscription> getInscriptionByStudentCode(String studentCode);

    List<Inscription> getAllInscriptions();
}
