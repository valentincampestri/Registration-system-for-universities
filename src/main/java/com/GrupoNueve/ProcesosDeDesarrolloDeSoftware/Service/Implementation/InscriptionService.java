package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Service.Implementation;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.InscriptionDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity.Course;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Service.IInscriptionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InscriptionService implements IInscriptionService {
    @Override
    public List<InscriptionDto> getInscriptionsByStudent(String studentId) {
        return null;
    }
}
