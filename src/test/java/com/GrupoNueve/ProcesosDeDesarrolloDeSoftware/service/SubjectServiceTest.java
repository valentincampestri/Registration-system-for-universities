package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.service;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.Response.MessageResponseDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.SubjectDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity.Subject;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Exception.BadRequestException;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Exception.InvalidArgsException;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Exception.NotFoundException;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Repository.Implementation.SubjectRepository;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Service.Implementation.SubjectService;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@DirtiesContext
@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class SubjectServiceTest {
    @Mock
    SubjectRepository subjectRepository;
    @InjectMocks
    SubjectService subjectService;

    @Test
    @DisplayName("createSubject - Subject already exists")
    public void createSubjectTestFailSubject() {
        // Arrange
        SubjectDto subjectDto = MockBuilder.mockSubjectDto();
        when(subjectRepository.getSubjectByCode(subjectDto.getSubjectCode())).thenReturn(Optional.of(MockBuilder.mockSubject()));
        // Act & Assert
        BadRequestException exception = assertThrows(BadRequestException.class, () -> subjectService.createSubject(subjectDto));
        Assertions.assertEquals("Subject already exists.", exception.getMessage());
    }

    @Test
    @DisplayName("createSubject - Workload must be greater than 0")
    public void createSubjectTestFailWorkload() {
        // Arrange
        SubjectDto subjectDto = MockBuilder.mockSubjectInvalidWorkloadDto();
        when(subjectRepository.getSubjectByCode(subjectDto.getSubjectCode())).thenReturn(Optional.empty());
        // Act & Assert
        InvalidArgsException exception = assertThrows(InvalidArgsException.class, () -> subjectService.createSubject(subjectDto));
        Assertions.assertEquals("Workload must be greater than 0.", exception.getMessage());
    }

    @Test
    @DisplayName("createSubject - One or more prerequisitesId don´t exist")
    public void createSubjectTestFailprerequisitesIdDontExist() {
        // Arrange
        SubjectDto subjectDto = MockBuilder.mockSubjectDto();
        when(subjectRepository.getSubjectByCode(anyString())).thenReturn(Optional.empty());
        // Act & Assert
        NotFoundException exception = assertThrows(NotFoundException.class, () -> subjectService.createSubject(subjectDto));
        Assertions.assertEquals("One or more prerequisitesId don´t exist.", exception.getMessage());
    }

    @Test
    @DisplayName("createSubject - A subject cannot be a prerequisite of itself.")
    public void createSubjectTestFailprerequisitesIdItself() {
        // Arrange
        SubjectDto subjectDto = MockBuilder.mockSubjectPrerequisiteItselfDto();
        when(subjectRepository.getSubjectByCode(anyString())).thenReturn(Optional.empty()).thenReturn(Optional.of(MockBuilder.mockSubjectPrerequisiteItself()));
        // Act & Assert
        InvalidArgsException exception = assertThrows(InvalidArgsException.class, () -> subjectService.createSubject(subjectDto));
        Assertions.assertEquals("A subject cannot be a prerequisite of itself.", exception.getMessage());
    }

    @Test
    @DisplayName("createSubject - Ok")
    public void createSubjectTestOk() {
        // Arrange
        SubjectDto subjectDto = MockBuilder.mockSubjectDto();
        when(subjectRepository.getSubjectByCode(anyString())).thenReturn(Optional.empty()).thenReturn(Optional.of(MockBuilder.mockSubject()));
        // Act
        MessageResponseDto result = subjectService.createSubject(subjectDto);
        // Assert
        Assertions.assertEquals("Subject created successfully.", result.getMessage());
    }

    @Test
    @DisplayName("getAllSubjects - There are no subjects")
    public void getAllSubjectsFail() {
        // Arrange
        when(subjectRepository.getAllSubject()).thenReturn(new ArrayList<>());
        // Act & Assert
        NotFoundException exception = assertThrows(NotFoundException.class, () -> subjectService.getAllSubjects());
        Assertions.assertEquals("There are no subjects.", exception.getMessage());
    }

    @Test
    @DisplayName("getAllSubjects - Ok")
    public void getAllSubjectsOk() {
        // Arrange
        List<Subject> subjectList = List.of(MockBuilder.mockSubject());
        when(subjectRepository.getAllSubject()).thenReturn(subjectList);
        List<SubjectDto> subjectDtoList = List.of(MockBuilder.mockSubjectDto());
        // Act
        List<SubjectDto> result = subjectService.getAllSubjects();
        // Assert
        Assertions.assertEquals(subjectDtoList, result);
    }
}
