package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Service;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.Response.InscriptionResponseDto;

import java.util.List;

public interface IInscriptionService {
    List<InscriptionResponseDto> getInscriptionsByStudent(String studentId);
}
