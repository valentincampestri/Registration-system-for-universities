package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.repository;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.entity.Course;

import java.util.List;
import java.util.Optional;

public interface ICourseRepository {
    void addCourse(Course course);

    void deleteCourse(String courseCode);

    void updateCourse(Course course);

    Optional<Course> getCourseByCode(String courseCode);

    List<Course> getAllCourses();

    List<Course> getCoursesByProfessor(String professorCode);
}
