package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.service;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.Response.MessageResponseDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.Request.CourseRequestDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.Response.CourseResponseDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity.*;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Exception.BadRequestException;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Exception.NotFoundException;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Repository.Implementation.CourseRepository;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Repository.Implementation.ProfessorRepository;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Repository.Implementation.SubjectRepository;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Service.Implementation.CourseService;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.utills.Mapper;
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
public class CourseServiceTest {
    @Mock
    CourseRepository courseRepository;
    @Mock
    ProfessorRepository professorRepository;
    @Mock
    SubjectRepository subjectRepository;
    @InjectMocks
    CourseService courseService;

    @Test
    @DisplayName("createCourse - Professor does not exist")
    public void createCourseTestFailProfessor() {
        // Arrange
        CourseRequestDto courseRequestDto = MockBuilder.mockCourseRequestDto();
        String professorId = "1";
        String subjectId = "1";
        when(professorRepository.getProfessorByCode(professorId)).thenReturn(Optional.empty());
        // Act & Assert
        NotFoundException exception = assertThrows(NotFoundException.class, () -> courseService.createCourse(courseRequestDto, professorId, subjectId));
        Assertions.assertEquals("Professor does not exist.", exception.getMessage());
    }

    @Test
    @DisplayName("createCourse - Subject does not exist")
    public void createCourseTestFailSubject() {
        // Arrange
        CourseRequestDto courseRequestDto = MockBuilder.mockCourseRequestDto();
        String professorId = "1";
        String subjectId = "1";
        when(professorRepository.getProfessorByCode(professorId)).thenReturn(Optional.of(MockBuilder.mockProfessor()));
        when(subjectRepository.getSubjectByCode(subjectId)).thenReturn(Optional.empty());
        // Act & Assert
        NotFoundException exception = assertThrows(NotFoundException.class, () -> courseService.createCourse(courseRequestDto, professorId, subjectId));
        Assertions.assertEquals("Subject does not exist.", exception.getMessage());
    }

    @Test
    @DisplayName("createCourse - Course already exists")
    public void createCourseTestFailCourse() {
        // Arrange
        CourseRequestDto courseRequestDto = MockBuilder.mockCourseRequestDto();
        String professorId = "1";
        String subjectId = "1";
        when(professorRepository.getProfessorByCode(professorId)).thenReturn(Optional.of(MockBuilder.mockProfessor()));
        when(subjectRepository.getSubjectByCode(subjectId)).thenReturn(Optional.of(MockBuilder.mockSubject()));
        Course course = Mapper.convertCourseRequestDtoToCourse(courseRequestDto, MockBuilder.mockProfessor(), MockBuilder.mockSubject());
        when(courseRepository.getCourseByCode(course.getCourseCode())).thenReturn(Optional.of(course));
        // Act & Assert
        BadRequestException exception = assertThrows(BadRequestException.class, () -> courseService.createCourse(courseRequestDto,"1","1"));
        Assertions.assertEquals("Course already exists.", exception.getMessage());
    }

    @Test
    @DisplayName("createCourse - Ok")
    public void createCourseTestOk() {
        // Arrange
        CourseRequestDto courseRequestDto = MockBuilder.mockCourseRequestDto();
        String professorId = "1";
        String subjectId = "1";
        when(professorRepository.getProfessorByCode(professorId)).thenReturn(Optional.of(MockBuilder.mockProfessor()));
        when(subjectRepository.getSubjectByCode(subjectId)).thenReturn(Optional.of(MockBuilder.mockSubject()));
        Course course = Mapper.convertCourseRequestDtoToCourse(courseRequestDto, MockBuilder.mockProfessor(), MockBuilder.mockSubject());
        when(courseRepository.getCourseByCode(course.getCourseCode())).thenReturn(Optional.empty());
        // Act
        MessageResponseDto result = courseService.createCourse(courseRequestDto,"1","1");
        // Assert
        Assertions.assertEquals("Course created successfully.", result.getMessage());
    }

    @Test
    @DisplayName("getAllCourses - There are no courses")
    public void getAllCoursesFail() {
        // Arrange
        when(courseRepository.getAllCourses()).thenReturn(new ArrayList<>());
        // Act & Assert
        NotFoundException exception = assertThrows(NotFoundException.class, () -> courseService.getAllCourses());
        Assertions.assertEquals("There are no courses.", exception.getMessage());
    }

    @Test
    @DisplayName("getAllCourses - Ok")
    public void getAllCoursesOk() {
        // Arrange
        List<Course> courseList = List.of(MockBuilder.mockCourse());
        when(courseRepository.getAllCourses()).thenReturn(courseList);
        List<CourseResponseDto> courseDtoList = List.of(MockBuilder.mockCourseResponseDto());
        // Act
        List<CourseResponseDto> result = courseService.getAllCourses();
        // Assert
        Assertions.assertEquals(courseDtoList, result);
    }
}
