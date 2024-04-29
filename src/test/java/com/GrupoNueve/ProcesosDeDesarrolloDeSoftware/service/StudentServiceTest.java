/*
package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.service;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.Request.StudentRequestDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity.Student;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity.Subject;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Exception.BadRequestException;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Repository.Implementation.StudentRepository;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Repository.Implementation.SubjectRepository;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Service.Implementation.StudentService;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.utils.Mapper;
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

}

*/
