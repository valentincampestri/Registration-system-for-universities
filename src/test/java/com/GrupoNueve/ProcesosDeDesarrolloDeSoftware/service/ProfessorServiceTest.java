package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.service;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.Request.CourseRequestDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.Request.ProfessorRequestDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.Request.ProfessorRequestDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.Response.MessageResponseDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Exception.BadRequestException;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Exception.NotFoundException;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Repository.Implementation.*;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Service.Implementation.CourseService;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Service.Implementation.FeeService;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Service.Implementation.InscriptionService;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Service.Implementation.ProfessorService;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.utils.MockBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@DirtiesContext
@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ProfessorServiceTest {
    @Mock
    ProfessorRepository professorRepository;
    @Mock
    SubjectRepository subjectRepository;
    @InjectMocks
    ProfessorService professorService;

    @Test
    @DisplayName("createProfessor - Professor already exists.")
    public void createProfessorTestFailProfessorAlreadyExists() {
        // Arrange
        ProfessorRequestDto professorRequestDto = MockBuilder.mockProfessorRequestDto();
        when(professorRepository.getProfessorByCode(professorRequestDto.getPersonID())).thenReturn(Optional.of(MockBuilder.mockProfessor()));

        // Act & Assert
        BadRequestException exception = assertThrows(BadRequestException.class, () -> professorService.createProfessor(professorRequestDto));
        Assertions.assertEquals("Professor already exists.", exception.getMessage());
    }

    @Test
    @DisplayName("createProfessor - One or more subjects don't exists.")
    public void createProfessorTestFailSubjectsDontExists() {
        // Arrange
        ProfessorRequestDto professorRequestDto = MockBuilder.mockProfessorRequestDto();
        when(professorRepository.getProfessorByCode(any())).thenReturn(Optional.empty());
        when(subjectRepository.getSubjectByCode(any())).thenReturn(Optional.empty());

        // Act & Assert
        BadRequestException exception = assertThrows(BadRequestException.class, () -> professorService.createProfessor(professorRequestDto));
        Assertions.assertEquals("One or more subjects don't exists.", exception.getMessage());
    }

    @Test
    @DisplayName("createProfessor - Ok.")
    public void createProfessorTestOk() {
        // Arrange
        ProfessorRequestDto professorRequestDto = MockBuilder.mockProfessorRequestDto();
        when(professorRepository.getProfessorByCode(any())).thenReturn(Optional.empty());
        when(subjectRepository.getSubjectByCode(any())).thenReturn(Optional.of(MockBuilder.mockSubject()));

        // Act
        MessageResponseDto result = professorService.createProfessor(professorRequestDto);
        // Assert
        Assertions.assertEquals("Professor created successfully.", result.getMessage());
    }
}
