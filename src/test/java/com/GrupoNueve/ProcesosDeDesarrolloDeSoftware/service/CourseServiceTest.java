package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.service;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.dto.response.MessageResponseDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.dto.request.CourseRequestDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.dto.response.CourseResponseDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.dto.ScheduleDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.entity.Course;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.exception.BadRequestException;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.exception.NotFoundException;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.repository.implementation.CourseRepository;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.repository.implementation.InscriptionRepository;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.repository.implementation.ProfessorRepository;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.repository.implementation.SubjectRepository;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.service.implementation.CourseService;
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

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@DirtiesContext
@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class CourseServiceTest {
    @Mock
    CourseRepository courseRepository;
    @Mock
    ProfessorRepository professorRepository;
    @Mock
    SubjectRepository subjectRepository;
    @Mock
    InscriptionRepository inscriptionRepository;

    @InjectMocks
    CourseService courseService;

    @Test
    @DisplayName("createCourse - Invalid day of week")
    public void createCourseTestFailDayOfWeek() {
        // Arrange
        CourseRequestDto courseRequestDto = MockBuilder.mockCourseRequestFailDayOfWeekDto();
        String subjectCode = "1";

        // Act & Assert
        BadRequestException exception = assertThrows(BadRequestException.class, () -> courseService.createCourse(courseRequestDto, subjectCode));
        Assertions.assertEquals("Invalid day of week.", exception.getMessage());
    }

    @Test
    @DisplayName("createCourse - Invalid classroom capacity")
    public void createCourseTestFailMaxCapacity() {
        // Arrange
        CourseRequestDto courseRequestDto = MockBuilder.mockCourseRequestFailMaxCapacityDto();
        String subjectCode = "1";

        // Act & Assert
        BadRequestException exception = assertThrows(BadRequestException.class, () -> courseService.createCourse(courseRequestDto, subjectCode));
        Assertions.assertEquals("Invalid classroom capacity, it must be from 10 to 150.", exception.getMessage());
    }

    @Test
    @DisplayName("createCourse - Subject does not exist")
    public void createCourseTestFailSubject() {
        // Arrange
        CourseRequestDto courseRequestDto = MockBuilder.mockCourseRequestDto();
        String subjectCode = "1";
        when(subjectRepository.getSubjectByCode(subjectCode)).thenReturn(Optional.empty());
        // Act & Assert
        NotFoundException exception = assertThrows(NotFoundException.class, () -> courseService.createCourse(courseRequestDto, subjectCode));
        Assertions.assertEquals("Subject does not exist.", exception.getMessage());
    }

    @Test
    @DisplayName("createCourse - no professors available")
    public void createCourseTestFailProfessor() {
        // Arrange
        CourseRequestDto courseRequestDto = MockBuilder.mockCourseRequestDto();
        String subjectCode = "2";
        when(subjectRepository.getSubjectByCode(subjectCode)).thenReturn(Optional.of(MockBuilder.mockSubject()));
        Course course = Mapper.convertCourseRequestDtoToCourse(courseRequestDto, MockBuilder.mockProfessor(), MockBuilder.mockSubject(), courseRequestDto.getDaysList().stream().map(DayOfWeek::valueOf).toList());
        when(courseRepository.getAllCourses()).thenReturn(List.of(course));
        when(professorRepository.getAllProfessors()).thenReturn(List.of(MockBuilder.mockProfessor()));
        // Act & Assert
        NotFoundException exception = assertThrows(NotFoundException.class, () -> courseService.createCourse(courseRequestDto, subjectCode));
        Assertions.assertEquals("There are no professors available for the specified subject and shift.", exception.getMessage());
    }

    @Test
    @DisplayName("createCourse - Course already exists")
    public void createCourseTestFailCourse() {
        // Arrange
        CourseRequestDto courseRequestDto = MockBuilder.mockCourseRequestDto();
        String subjectCode = "1";
        when(subjectRepository.getSubjectByCode(subjectCode)).thenReturn(Optional.of(MockBuilder.mockSubjectTwo()));
        Course course = Mapper.convertCourseRequestDtoToCourse(MockBuilder.mockCourseRequestDto(), MockBuilder.mockProfessorTwo(), MockBuilder.mockSubject(), courseRequestDto.getDaysList().stream().map(DayOfWeek::valueOf).toList());
        when(courseRepository.getAllCourses()).thenReturn(List.of(course));
        when(professorRepository.getAllProfessors()).thenReturn(List.of(MockBuilder.mockProfessor()));
        // Act & Assert
        BadRequestException exception = assertThrows(BadRequestException.class, () -> courseService.createCourse(courseRequestDto,"1"));
        Assertions.assertEquals("Course already exists.", exception.getMessage());
    }

    @Test
    @DisplayName("createCourse - Ok")
    public void createCourseTestOk() {
        // Arrange
        CourseRequestDto courseRequestDto = MockBuilder.mockCourseRequestDto();
        String subjectCode = "1";
        when(subjectRepository.getSubjectByCode(subjectCode)).thenReturn(Optional.of(MockBuilder.mockSubjectTwo()));
        Course course = Mapper.convertCourseRequestDtoToCourse(MockBuilder.mockCourseRequest2Dto(), MockBuilder.mockProfessorTwo(), MockBuilder.mockSubject(), courseRequestDto.getDaysList().stream().map(DayOfWeek::valueOf).toList());
        when(courseRepository.getAllCourses()).thenReturn(List.of(course));
        when(professorRepository.getAllProfessors()).thenReturn(List.of(MockBuilder.mockProfessor()));
        // Act
        MessageResponseDto result = courseService.createCourse(courseRequestDto,"1");
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
    @DisplayName("getCoursesBySubject - Subject does not exist")
    public void getCoursesBySubjectTestNotFoundException() {
        // Arrange
        String subjectCode = "03493490384902390284";
        List<Course> courseList = List.of(MockBuilder.mockCourse());
        when(courseRepository.getAllCourses()).thenReturn(courseList);
        when(subjectRepository.getSubjectByCode(subjectCode)).thenReturn(Optional.empty());

        // Act & Assert
        NotFoundException exception = assertThrows(NotFoundException.class, () -> courseService.getCoursesBySubject(subjectCode));
        Assertions.assertEquals("Subject does not exist.", exception.getMessage());
    }

    @Test
    @DisplayName("getCoursesBySubject - No courses found for subject")
    public void getCoursesBySubjectTestNoCourses() {
        // Arrange
        String subjectCode = "123";
        when(courseRepository.getAllCourses()).thenReturn(new ArrayList<>());

        // Act & Assert
        Assertions.assertThrows(NotFoundException.class, () -> courseService.getCoursesBySubject(subjectCode));
    }

    @Test
    @DisplayName("getCoursesBySubject - Courses found for subject")
    public void getCoursesBySubjectTestCoursesFound() {
        // Arrange
        String subjectCode = "1";
        List<Course> courses = new ArrayList<>();
        courses.add(MockBuilder.mockCourse());
        when(courseRepository.getAllCourses()).thenReturn(courses);
        when(subjectRepository.getSubjectByCode(subjectCode)).thenReturn(Optional.of(MockBuilder.mockSubjectTwo()));

        // Act
        List<CourseResponseDto> response = courseService.getCoursesBySubject(subjectCode);

        // Assert
        Assertions.assertEquals(List.of(MockBuilder.mockCourseResponseDto()), response);
    }

    @Test
    @DisplayName("getCoursesByShift - Shift does not exist")
    public void getCoursesByShiftTestNotFoundException() {
        // Arrange
        String shift = "03493490384902390284";

        // Act & Assert
        BadRequestException exception = assertThrows(BadRequestException.class, () -> courseService.getCoursesByShift(shift));
        Assertions.assertEquals("Invalid shift.", exception.getMessage());
    }

    @Test
    @DisplayName("getCoursesByShift - No courses found for shift")
    public void getCoursesByShiftTestNoCourses() {
        // Arrange
        String shift = "night";
        when(courseRepository.getAllCourses()).thenReturn(new ArrayList<>());

        // Act & Assert
        Assertions.assertThrows(NotFoundException.class, () -> courseService.getCoursesByShift(shift));
    }

    @Test
    @DisplayName("getCoursesByShift - Courses found for shift")
    public void getCoursesByShiftTestCoursesFound() {
        // Arrange
        String shift = "MORNING";
        List<Course> courses = new ArrayList<>();
        courses.add(MockBuilder.mockCourse());
        when(courseRepository.getAllCourses()).thenReturn(courses);

        // Act
        List<CourseResponseDto> response = courseService.getCoursesByShift(shift);

        // Assert
        Assertions.assertEquals(List.of(MockBuilder.mockCourseResponseDto()),response);
    }

    @Test
    @DisplayName("getCoursesByProfessor - No courses found for professor")
    public void getCoursesByProfessorTestNoCourses() {
        // Arrange
        String professorCode = "123";
        when(courseRepository.getAllCourses()).thenReturn(new ArrayList<>());

        // Act & Assert
        Assertions.assertThrows(NotFoundException.class, () -> courseService.getCoursesByProfessor(professorCode));
    }

    @Test
    @DisplayName("getCoursesByProfessor - Courses found for professor")
    public void getCoursesByProfessorTestCoursesFound() {
        // Arrange
        String professorCode = "string";
        List<Course> courses = new ArrayList<>();
        courses.add(MockBuilder.mockCourse());
        when(courseRepository.getAllCourses()).thenReturn(courses);

        // Act
        List<CourseResponseDto> response = courseService.getCoursesByProfessor(professorCode);

        // Assert
        Assertions.assertNotNull(response);
    }

    @Test
    @DisplayName("getScheduleByCourse - Course found")
    public void getScheduleByCourseTestCourseFound() {
        // Arrange
        String courseCode = "1";
        Course course = MockBuilder.mockCourse();
        course.setCourseCode(courseCode);
        ScheduleDto expectedScheduleDto = new ScheduleDto("Schedule");

        when(courseRepository.getCourseByCode(courseCode)).thenReturn(Optional.of(course));

        // Act
        ScheduleDto result = courseService.getScheduleByCourse(courseCode);

        // Assert
        Assertions.assertEquals(expectedScheduleDto, result);
    }

    @Test
    @DisplayName("getScheduleByCourse - Course not found")
    public void getScheduleByCourseTestCourseNotFound() {
        // Arrange
        String courseCode = "1";
        when(courseRepository.getCourseByCode(courseCode)).thenReturn(Optional.empty());

        // Act & Assert
        NotFoundException exception = assertThrows(NotFoundException.class, () -> courseService.getScheduleByCourse(courseCode));
        Assertions.assertEquals("Course not found with ID: " + courseCode, exception.getMessage());

    }

    @Test
    @DisplayName("getTermReportByProfessor - Professor does not exist")
    public void getTermReportByProfessorTestProffesorNotExist() {
        // Arrange
        String professorCode = "QWERTYUIK";
        String reportFormat = "PDF";
        String termCode = "1T-2024";
        when(professorRepository.getProfessorByCode(professorCode)).thenReturn(Optional.empty());

        // Act && Assert
        NotFoundException exception = assertThrows(NotFoundException.class, () -> courseService.getTermReportByProfessor(professorCode, reportFormat, termCode));
        Assertions.assertEquals("Professor does not exist.", exception.getMessage());
    }

    @Test
    @DisplayName("getTermReportByProfessor - No courses found for professor in this term")
    public void getTermReportByProfessorTestNoCourses() {
        // Arrange
        String professorCode = "string";
        String termCode = "1T-2024";
        when(professorRepository.getProfessorByCode(professorCode)).thenReturn(Optional.of(MockBuilder.mockProfessor()));
        String reportFormat = "PDF";
        when(courseRepository.getCoursesByProfessor(professorCode)).thenReturn(Collections.emptyList());

        // Act && Assert
        BadRequestException exception = assertThrows(BadRequestException.class, () -> courseService.getTermReportByProfessor(professorCode, reportFormat, termCode));
        Assertions.assertEquals("There are no courses assigned for the professor with ID: " + professorCode + " in the term with ID: " + termCode, exception.getMessage());
    }

    @Test
    @DisplayName("getTermReportByProfessor - Invalid report format")
    public void getTermReportByProfessorTestInvalidReportFormat() {
        // Arrange
        String professorCode = "string";
        when(professorRepository.getProfessorByCode(professorCode)).thenReturn(Optional.of(MockBuilder.mockProfessor()));
        String reportFormat = "mp4";
        String termCode = "1T-2024";
        List<Course> courses = List.of(MockBuilder.mockCourse());
        when(courseRepository.getCoursesByProfessor(professorCode)).thenReturn(courses);

        // Act && Assert
        BadRequestException exception = assertThrows(BadRequestException.class, () -> courseService.getTermReportByProfessor(professorCode, reportFormat, termCode));
        Assertions.assertEquals("Invalid report format.", exception.getMessage());
    }

    @Test
    @DisplayName("getTermReportByProfessor - OK")
    public void getTermReportByProfessorTestOk() {
        // Arrange
        String professorCode = "string";
        when(professorRepository.getProfessorByCode(professorCode)).thenReturn(Optional.of(MockBuilder.mockProfessor()));
        String reportFormat = "Excel";
        String termCode = "1T-2024";
        List<Course> courses = List.of(MockBuilder.mockCourse());
        when(courseRepository.getCoursesByProfessor(professorCode)).thenReturn(courses);

        // Act
        MessageResponseDto response = courseService.getTermReportByProfessor(professorCode, reportFormat, termCode);

        // Assert
        Assertions.assertEquals("Term Report generated.", response.getMessage());
    }

    @Test
    @DisplayName("getCoursesBySubjectAndShift - Courses not found for specified subject and shift")
    public void getCoursesBySubectAndShiftTestNotFound() {
        // Arrange
        String subjectCode = "14";
        String shift = "MORNING";

        List<Course> courses = new ArrayList<>();
        courses.add(MockBuilder.mockCourse());
        when(courseRepository.getAllCourses()).thenReturn(courses);
        when(subjectRepository.getSubjectByCode(subjectCode)).thenReturn(Optional.of(MockBuilder.mockSubject()));

        // Act && Assert
        NotFoundException exception = assertThrows(NotFoundException.class, () -> courseService.getCoursesBySubectAndShift(subjectCode, shift));
        Assertions.assertEquals("There are no courses with the specified subject and shift.", exception.getMessage());
    }

    @Test
    @DisplayName("getCoursesBySubjectAndShift - Ok")
    public void getCoursesBySubectAndShiftOk() {
        // Arrange
        String subjectCode = "1";
        String shift = "MORNING";

        List<Course> courses = new ArrayList<>();
        courses.add(MockBuilder.mockCourse());
        when(courseRepository.getAllCourses()).thenReturn(courses);
        when(subjectRepository.getSubjectByCode(subjectCode)).thenReturn(Optional.of(MockBuilder.mockSubjectTwo()));

        // Act
        List<CourseResponseDto> response = courseService.getCoursesBySubectAndShift(subjectCode, shift);

        // Assert
        Assertions.assertEquals(List.of(MockBuilder.mockCourseResponseDto()), response);
    }
}
