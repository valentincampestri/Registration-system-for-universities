package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Repository.Implementation;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity.Inscription;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Repository.IInscriptionRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class InscriptionRepository implements IInscriptionRepository {
    @Override
    public List<Inscription> getAllInscriptions() {
        return null;
    }
}
