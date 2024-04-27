package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Repository.Implementation;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity.Inscription;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Repository.IInscriptionRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class InscriptionRepository implements IInscriptionRepository {

    private List<Inscription> inscriptionsList = new ArrayList<>();

    @Override
    public void addInscription(Inscription inscription) {
        inscriptionsList.add(inscription);
    }

    @Override
    public void deleteInscription(String inscriptionId) {
        inscriptionsList.removeIf(inscription -> inscription.getId().equals(inscriptionId));
    }

    @Override
    public void updateInscription(Inscription inscription) {
        inscriptionsList.remove(inscription);
        inscriptionsList.add(inscription);
    }

    @Override
    public Optional<Inscription> getInscriptionById(String inscriptionId) {
        return inscriptionsList.stream()
                .filter(inscription -> inscription.getId().equals(inscriptionId))
                .findFirst();
    }

    @Override
    public List<Inscription> getAllInscriptions() {
        return inscriptionsList;
    }
}
