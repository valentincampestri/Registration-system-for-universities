package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Service;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.InscriptionDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity.Course;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity.Inscription;

import java.util.List;

public interface IInscriptionService {
    public List<InscriptionDto> getInscriptionsByStudent(String studentId);
    public void addInscription(String studentId, Course course);

}
