package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Repository;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity.Course;

import java.util.List;
import java.util.Optional;

public interface ICourseRepository {
    void addCourse(Course course);

    void deleteCourse(String courseId);

    void updateCourse(Course course);

    Optional<Course> getCourseById(String courseId);

    List<Course> getAllCourses();
}
