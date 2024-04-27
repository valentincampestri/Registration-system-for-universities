package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.service;


import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.CourseDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.MessageResponseDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity.*;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Exception.BadRequestException;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Exception.NotFoundException;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Repository.Implementation.CourseRepository;
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
    @InjectMocks
    CourseService courseService;

    @Test
    @DisplayName("createCourse - Course already exists")
    public void createCourseTestFail() {
        // Arrange
        CourseDto courseDto = MockBuilder.mockCourseDto();
        Course course = Mapper.convertCourseDtoToCourse(courseDto);
        when(courseRepository.getCourseById(course.getCourseID())).thenReturn(Optional.of(course));
        // Act & Assert
        BadRequestException exception = assertThrows(BadRequestException.class, () -> courseService.createCourse(courseDto));
        Assertions.assertEquals("Course already exists.", exception.getMessage());
    }

    @Test
    @DisplayName("createCourse - Ok")
    public void createCourseTestOk() {
        // Arrange
        CourseDto courseDto = MockBuilder.mockCourseDto();
        Course course = Mapper.convertCourseDtoToCourse(courseDto);
        when(courseRepository.getCourseById(course.getCourseID())).thenReturn(Optional.empty());
        // Act
        MessageResponseDto result = courseService.createCourse(courseDto);
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
        List<CourseDto> courseDtoList = List.of(MockBuilder.mockCourseDto());
        // Act
        List<CourseDto> result = courseService.getAllCourses();
        // Assert
        Assertions.assertEquals(courseDtoList, result);
    }
}
