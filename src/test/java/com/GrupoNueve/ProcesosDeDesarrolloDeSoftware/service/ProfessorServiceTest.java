package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.service;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.dto.request.ProfessorRequestDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.dto.response.MessageResponseDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.exception.BadRequestException;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.repository.implementation.*;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.service.implementation.ProfessorService;
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
        BadRequestException exception = assertThrows(BadRequestException.class,
                () -> professorService.createProfessor(professorRequestDto));
        Assertions.assertEquals("One or more subjects don't exists.", exception.getMessage());
    }

    @Test
    @DisplayName("createProfessor - Invalid day of week.")
    public void createProfessorTestFailInvalidDayOfWeek() {
        // Arrange
        ProfessorRequestDto professorRequestDto = MockBuilder.mockProfessorRequestDtoWithInvalidDay();
        when(professorRepository.getProfessorByCode(any())).thenReturn(Optional.empty());
        when(subjectRepository.getSubjectByCode(any())).thenReturn(Optional.of(MockBuilder.mockSubject()));

        // Act & Assert
        BadRequestException exception = assertThrows(BadRequestException.class,
                () -> professorService.createProfessor(professorRequestDto));
        Assertions.assertEquals("Invalid day of week.", exception.getMessage());
    }

    @Test
    @DisplayName("createProfessor - Invalid shift.")
    public void createProfessorTestFailInvalidShift() {
        // Arrange
        ProfessorRequestDto professorRequestDto = MockBuilder.mockProfessorRequestDtoWithInvalidShift();
        when(professorRepository.getProfessorByCode(any())).thenReturn(Optional.empty());
        when(subjectRepository.getSubjectByCode(any())).thenReturn(Optional.of(MockBuilder.mockSubject()));

        // Act & Assert
        BadRequestException exception = assertThrows(BadRequestException.class,
                () -> professorService.createProfessor(professorRequestDto));
        Assertions.assertEquals("Invalid shift.", exception.getMessage());
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

    @Test
    @DisplayName("calculateMonthlyWorkload - Professor does not exist.")
    public void calculateMonthlyWorkloadTestFailProfessorDoesNotExist() {
        // Arrange
        when(professorRepository.getProfessorByCode(any())).thenReturn(Optional.empty());

        // Act & Assert
        BadRequestException exception = assertThrows(BadRequestException.class,
                () -> professorService.calculateMonthlyWorkload("123"));
        Assertions.assertEquals("Professor does not exist.", exception.getMessage());
    }

    @Test
    @DisplayName("calculateMonthlyWorkload - Ok.")
    public void calculateMonthlyWorkloadTestOk() {
        // Arrange
        when(professorRepository.getProfessorByCode(any())).thenReturn(Optional.of(MockBuilder.mockProfessor()));

        // Act
        MessageResponseDto result = professorService.calculateMonthlyWorkload("123");
        // Assert
        Assertions.assertEquals("Total hours: 10", result.getMessage());
    }
}
