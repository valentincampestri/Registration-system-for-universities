package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Repository.Implementation;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity.Course;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Repository.ICourseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CourseRepository implements ICourseRepository {
    @Override
    public List<Course> getCourseById(Integer courseId) {
        return null;
    }

    @Override
    public List<Course> getAllCourses() {
        return null;
    }
}
