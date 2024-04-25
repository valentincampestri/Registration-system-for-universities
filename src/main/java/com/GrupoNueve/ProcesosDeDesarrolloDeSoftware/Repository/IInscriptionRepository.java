package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Repository;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity.Course;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity.Inscription;

import java.util.List;
import java.util.Optional;

public interface IInscriptionRepository {
    void addInscription(Inscription inscription);
    void deleteInscription(Integer inscriptionId);
    void updateInscription(Inscription inscription);
    Optional<Inscription> getInscriptionById(Integer inscriptionId);
    List<Inscription> getAllInscriptions();
}
