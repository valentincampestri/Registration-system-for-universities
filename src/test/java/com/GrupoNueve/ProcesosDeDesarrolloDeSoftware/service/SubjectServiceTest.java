package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.service;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.Request.CourseRequestDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.Response.CourseResponseDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.Response.MessageResponseDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.SubjectDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity.Course;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

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
        when(subjectRepository.getSubjectById(subjectDto.getSubjectID())).thenReturn(Optional.of(MockBuilder.mockSubject()));
        // Act & Assert
        BadRequestException exception = assertThrows(BadRequestException.class, () -> subjectService.createSubject(subjectDto));
        Assertions.assertEquals("Subject already exists.", exception.getMessage());
    }

    @Test
    @DisplayName("createSubject - Workload must be greater than 0")
    public void createSubjectTestFailWorkload() {
        // Arrange
        SubjectDto subjectDto = MockBuilder.mockSubjectInvalidWorkloadDto();
        when(subjectRepository.getSubjectById(subjectDto.getSubjectID())).thenReturn(Optional.empty());
        // Act & Assert
        InvalidArgsException exception = assertThrows(InvalidArgsException.class, () -> subjectService.createSubject(subjectDto));
        Assertions.assertEquals("Workload must be greater than 0.", exception.getMessage());
    }

    /*@Test
    @DisplayName("createSubject - One or more Prerequisites don´t exist")
    public void createSubjectTestFailPrerequisitesDontExist() {
        // Arrange
        SubjectDto subjectDto = MockBuilder.mockSubjectDto();
        when(subjectRepository.getSubjectById(subjectDto.getSubjectID())).thenReturn(Optional.empty());
        for (String prerequisite : subjectDto.getPrerequisites()) {
            when(subjectRepository.getSubjectById(prerequisite)).thenReturn(Optional.empty());
        }
        // Act & Assert
        NotFoundException exception = assertThrows(NotFoundException.class, () -> subjectService.createSubject(subjectDto));
        Assertions.assertEquals("One or more Prerequisites don´t exist.", exception.getMessage());
    }*/

    /*@Test
    @DisplayName("createSubject - A subject cannot be a prerequisite of itself.")
    public void createSubjectTestFailPrerequisitesItself() {
        // Arrange
        SubjectDto subjectDto = MockBuilder.mockSubjectDto();
        when(subjectRepository.getSubjectById(subjectDto.getSubjectID())).thenReturn(Optional.empty());
        for (String prerequisite : subjectDto.getPrerequisites()) {
            when(subjectRepository.getSubjectById(prerequisite)).thenReturn(Optional.of(MockBuilder.mockSubject2()));
        }
        // Act & Assert
        InvalidArgsException exception = assertThrows(InvalidArgsException.class, () -> subjectService.createSubject(subjectDto));
        Assertions.assertEquals("A subject cannot be a prerequisite of itself.", exception.getMessage());
    }*/

/*    @Test
    @DisplayName("createSubject - Ok")
    public void createSubjectTestOk() {
        // Arrange
        SubjectDto subjectDto = MockBuilder.mockSubjectDto();
        when(subjectRepository.getSubjectById(subjectDto.getSubjectID())).thenReturn(Optional.empty());
        for (String prerequisite : subjectDto.getPrerequisites()) {
            when(subjectRepository.getSubjectById(prerequisite)).thenReturn(Optional.of(MockBuilder.mockSubject2()));
        }
        // Act
        MessageResponseDto result = subjectService.createSubject(subjectDto);
        // Assert
        Assertions.assertEquals("Course created successfully.", result.getMessage());
    }*/

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
