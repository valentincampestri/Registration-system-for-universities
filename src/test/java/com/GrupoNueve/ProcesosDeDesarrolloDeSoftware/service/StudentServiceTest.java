package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.service;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.dto.request.StudentRequestDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.dto.response.MessageResponseDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.exception.BadRequestException;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.repository.implementation.StudentRepository;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.repository.implementation.SubjectRepository;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.service.implementation.StudentService;
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
public class StudentServiceTest {

    @Mock
    StudentRepository studentRepository;

    @Mock
    SubjectRepository subjectRepository;

    @InjectMocks
    StudentService studentService;

    @Test
    @DisplayName("createStudent - Student already exists.")
    public void createStudentTestFailStudentAlreadyExists() {
        // Arrange
        StudentRequestDto studentRequestDto = MockBuilder.mockStudentRequestDto();
        when(studentRepository.getStudentByCode(studentRequestDto.getPersonCode())).thenReturn(Optional.of(MockBuilder.mockStudent()));

        // Act & Assert
        BadRequestException exception = assertThrows(BadRequestException.class, () -> studentService.createStudent(studentRequestDto));
        Assertions.assertEquals("Student already exists.", exception.getMessage());
    }

    @Test
    @DisplayName("createStudent - One or more subjects don't exists.")
    public void createStudentTestFailSubjectsDontExists() {
        // Arrange
        StudentRequestDto studentRequestDto = MockBuilder.mockStudentRequestDto();
        when(studentRepository.getStudentByCode(any())).thenReturn(Optional.empty());
        when(subjectRepository.getSubjectByCode(any())).thenReturn(Optional.empty());

        // Act & Assert
        BadRequestException exception = assertThrows(BadRequestException.class, () -> studentService.createStudent(studentRequestDto));
        Assertions.assertEquals("One or more subjects don't exists.", exception.getMessage());
    }

    @Test
    @DisplayName("createStudent - Ok.")
    public void createStudentTestOk() {
        // Arrange
        StudentRequestDto studentRequestDto = MockBuilder.mockStudentRequestDto();
        when(studentRepository.getStudentByCode(any())).thenReturn(Optional.empty());
        when(subjectRepository.getSubjectByCode(any())).thenReturn(Optional.of(MockBuilder.mockSubject()));

        // Act
        MessageResponseDto result = studentService.createStudent(studentRequestDto);
        // Assert
        Assertions.assertEquals("Student created successfully.", result.getMessage());
    }

}

