package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Service;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.InscriptionDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity.Course;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity.Inscription;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IInscriptionService {
    List<InscriptionDto> getInscriptionsByStudent(String studentId);
}
