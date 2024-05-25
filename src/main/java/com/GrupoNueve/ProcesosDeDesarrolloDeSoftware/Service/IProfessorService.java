package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Service;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.Request.ProfessorRequestDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.Response.MessageResponseDto;

public interface IProfessorService {
    MessageResponseDto createProfessor(ProfessorRequestDto professorRequestDto);
    MessageResponseDto calculateMonthlyWorkload(String professorCode);
}