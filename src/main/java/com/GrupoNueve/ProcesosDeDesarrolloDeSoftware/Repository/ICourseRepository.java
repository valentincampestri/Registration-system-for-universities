package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Repository;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity.Course;

import java.util.List;

public interface ICourseRepository {
    public List<Course> getCourseById(Integer courseId);
    public List<Course> getAllCourses();


}
