package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Repository;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity.Course;

import java.util.List;
import java.util.Optional;

public interface ICourseRepository {
    void addCourse(Course course);
    void deleteCourse(Integer courseId);
    void updateCourse(Course course);
    Optional<Course> getCourseById(Integer courseId);
    List<Course> getAllCourses();
}
