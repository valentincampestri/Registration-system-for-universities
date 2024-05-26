package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.service;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.dto.request.ProfessorRequestDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.dto.response.MessageResponseDto;

public interface IProfessorService {
    MessageResponseDto createProfessor(ProfessorRequestDto professorRequestDto);
    MessageResponseDto calculateMonthlyWorkload(String professorCode);
}