package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Service.Implementation;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Repository.IProfessorRepository;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Service.IProfessorService;
import org.springframework.stereotype.Service;

@Service
public class ProfessorService implements IProfessorService {
    IProfessorRepository professorRepository;

    public ProfessorService(IProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }
}
