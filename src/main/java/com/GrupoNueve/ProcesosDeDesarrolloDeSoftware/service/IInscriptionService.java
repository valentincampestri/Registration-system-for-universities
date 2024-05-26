package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.service;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.dto.request.InscriptionRequestDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.dto.response.MessageResponseDto;

public interface IInscriptionService {

    MessageResponseDto createInscription(InscriptionRequestDto inscriptionRequestDto, String studentCode);
}
