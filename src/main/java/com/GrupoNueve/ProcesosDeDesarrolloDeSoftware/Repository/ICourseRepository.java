package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Repository;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity.Course;

import java.util.List;
import java.util.Optional;

public interface ICourseRepository {
    void addCourse(Course course);

    void deleteCourse(String courseCode);

    void updateCourse(Course course);

    Optional<Course> getCourseByCode(String courseCode);

    List<Course> getAllCourses();
}
