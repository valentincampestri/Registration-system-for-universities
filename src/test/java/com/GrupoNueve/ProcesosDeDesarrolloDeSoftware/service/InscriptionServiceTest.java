package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.service;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.Request.InscriptionRequestDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Exception.BadRequestException;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Exception.NotFoundException;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Repository.IStudentRepository;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Repository.Implementation.CourseRepository;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Repository.Implementation.InscriptionRepository;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Service.Implementation.InscriptionService;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.utils.MockBuilder;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.utils.Utils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@DirtiesContext
@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class InscriptionServiceTest {
    @Mock
    InscriptionRepository inscriptionRepository;

    @Mock
    IStudentRepository studentRepository;

    @Mock
    CourseRepository courseRepository;
    @Mock
    Utils utils;
    @InjectMocks
    InscriptionService inscriptionService;

    @Test
    @DisplayName("create - Student does not exist")
    public void createInscriptionFailStudent() {
        // Arrange
        InscriptionRequestDto inscriptionRequestDto = MockBuilder.mockInscriptionRequestDto();
        String studentCode = "0";
        when(studentRepository.getStudentByCode(studentCode)).thenReturn(Optional.empty());
        // Act & Assert
        NotFoundException exception = assertThrows(NotFoundException.class, () -> inscriptionService.createInscription(inscriptionRequestDto, studentCode));
        Assertions.assertEquals("Student does not exist.", exception.getMessage());
    }

    @Test
    @DisplayName("create - Course does not exist")
    public void createInscriptionFailCourse() {
        // Arrange
        InscriptionRequestDto inscriptionRequestDto = MockBuilder.mockInscriptionRequestDto();
        String studentCode = MockBuilder.mockStudent().getPersonCode();
        String courseCode = "CS101";
        when(studentRepository.getStudentByCode(studentCode)).thenReturn(Optional.of(MockBuilder.mockStudent()));
        when(courseRepository.getCourseByCode(courseCode)).thenReturn(Optional.empty());
        // Act & Assert
        NotFoundException exception = assertThrows(NotFoundException.class, () -> inscriptionService.createInscription(inscriptionRequestDto, studentCode));
        Assertions.assertEquals("Course does not exist.", exception.getMessage());
    }

//    @Test
//    @DisplayName("create - Already enrolled")
//    public void createInscriptionFailAlreadyEnrolled() {
//        // Arrange
//        InscriptionRequestDto inscriptionRequestDto = MockBuilder.mockInscriptionRequestDto();
//        String studentCode = "0";
//        String courseCode = "CS101";
//
//        when(studentRepository.getStudentByCode(studentCode)).thenReturn(Optional.of(MockBuilder.mockStudent()));
//        when(courseRepository.getCourseByCode(courseCode)).thenReturn(Optional.of(MockBuilder.mockCourse()));
//        when(inscriptionRepository.getInscriptionByStudentCode(studentCode)).thenReturn(List.of(MockBuilder.mockInscription()));
//        // Act & Assert
//        NotFoundException exception = assertThrows(NotFoundException.class, () -> inscriptionService.createInscription(inscriptionRequestDto, studentCode));
//        Assertions.assertEquals("You are already enrolled in one or more subjects that you are trying to enroll in this term.", exception.getMessage());
//    }

    @Test
    @DisplayName("create - Course is already full")
    public void createInscriptionFailMaxCapacity() {
        // Arrange
        InscriptionRequestDto inscriptionRequestDto = MockBuilder.mockInscriptionRequestMaxCapacityDto();
        String studentCode = MockBuilder.mockStudent().getPersonCode();
        String courseCode = "CS500";
        when(studentRepository.getStudentByCode(studentCode)).thenReturn(Optional.of(MockBuilder.mockStudent()));
        when(courseRepository.getCourseByCode(courseCode)).thenReturn(Optional.of(MockBuilder.mockCourseMaxCapacity()));
        // Act & Assert
        BadRequestException exception = assertThrows(BadRequestException.class, () -> inscriptionService.createInscription(inscriptionRequestDto, studentCode));
        Assertions.assertEquals("The course " + courseCode + " is already full.", exception.getMessage());
    }

    @Test
    @DisplayName("create - Ok")
    public void createOk() {
        // Arrange
        InscriptionRequestDto inscriptionRequestDto = MockBuilder.mockInscriptionRequestDto();
        String studentCode = "0";
        String courseCode = "CS101";
        when(studentRepository.getStudentByCode(studentCode)).thenReturn(Optional.of(MockBuilder.mockStudent()));
        when(courseRepository.getCourseByCode(courseCode)).thenReturn(Optional.of(MockBuilder.mockCourse()));
        // Act
        String response = inscriptionService.createInscription(inscriptionRequestDto, studentCode).getMessage();
        // Assert
        Assertions.assertEquals("You have successfully enrolled in the selected courses. Now you can pay according to your student code.", response);
    }
}