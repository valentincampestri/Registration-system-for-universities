package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.service;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.Response.MessageResponseDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.Request.CourseRequestDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.Response.CourseResponseDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.ScheduleDto;
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
import java.util.Collections;
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

    @Test
    @DisplayName("getCoursesBySubject - No courses found for subject")
    public void getCoursesBySubjectNoCoursesTest() {
        // Arrange
        String subjectId = "123";
        when(courseRepository.getAllCourses()).thenReturn(new ArrayList<>());

        // Act & Assert
        Assertions.assertThrows(NotFoundException.class, () -> courseService.getCoursesBySubject(subjectId));
    }

    @Test
    @DisplayName("getCoursesBySubject - Courses found for subject")
    public void getCoursesBySubjectCoursesFoundTest() {
        // Arrange
        String subjectId = "123";
        List<Course> courses = new ArrayList<>();
        courses.add(MockBuilder.mockCourse());
        when(courseRepository.getAllCourses()).thenReturn(courses);

        // Act
        List<CourseResponseDto> response = courseService.getCoursesBySubject(subjectId);

        // Assert
        Assertions.assertNotNull(response);
    }

    @Test
    @DisplayName("getCoursesByProfessor - No courses found for professor")
    public void getCoursesByProfessorNoCoursesTest() {
        // Arrange
        String professorId = "123";
        when(courseRepository.getAllCourses()).thenReturn(new ArrayList<>());

        // Act & Assert
        Assertions.assertThrows(NotFoundException.class, () -> courseService.getCoursesByProfessor(professorId));
    }

    @Test
    @DisplayName("getCoursesByProfessor - Courses found for professor")
    public void getCoursesByProfessorCoursesFoundTest() {
        // Arrange
        String professorId = "123";
        List<Course> courses = new ArrayList<>();
        courses.add(MockBuilder.mockCourse());
        when(courseRepository.getAllCourses()).thenReturn(courses);

        // Act
        List<CourseResponseDto> response = courseService.getCoursesByProfessor(professorId);

        // Assert
        Assertions.assertNotNull(response);
    }

    @Test
    @DisplayName("getScheduleByCourse - Course found")
    public void getScheduleByCourseTestCourseFound() {
        // Arrange
        String courseId = "1";
        Course course = MockBuilder.mockCourse();
        course.setCourseCode(courseId);
        ScheduleDto expectedScheduleDto = new ScheduleDto("Schedule");

        when(courseRepository.getCourseByCode(courseId)).thenReturn(Optional.of(course));

        // Act
        ScheduleDto result = courseService.getScheduleByCourse(courseId);

        // Assert
        Assertions.assertEquals(expectedScheduleDto, result);
    }

    @Test
    @DisplayName("getScheduleByCourse - Course not found")
    public void getScheduleByCourseTestCourseNotFound() {
        // Arrange
        String courseId = "1";
        when(courseRepository.getCourseByCode(courseId)).thenReturn(Optional.empty());

        // Act & Assert
        Assertions.assertThrows(NotFoundException.class, () -> courseService.getScheduleByCourse(courseId));
    }
    @Test
    @DisplayName("generateReport - No courses found for professor")
    public void generateReportNoCoursesTest() {
        // Arrange
        String professorId = "123";
        when(courseRepository.getCoursesByProfessor(professorId)).thenReturn(Collections.emptyList());

        // Act
        MessageResponseDto response = courseService.generateReport(professorId);

        // Assert
        Assertions.assertEquals("No hay cursos asignados para el docente con ID: " + professorId, response.getMessage());
    }

    @Test
    @DisplayName("generateReport - Courses found for professor")
    public void generateReportCoursesFoundTest() {
        // Arrange
        String professorId = "123";
        List<Course> courses = List.of(MockBuilder.mockCourse());
        when(courseRepository.getCoursesByProfessor(professorId)).thenReturn(courses);

        // Act
        MessageResponseDto response = courseService.generateReport(professorId);

        // Assert
        Assertions.assertTrue(response.getMessage().startsWith("Generando reporte en PDF para el docente con ID: " + professorId));
    }

}
