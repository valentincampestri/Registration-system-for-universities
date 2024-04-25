package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Repository.Implementation;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity.Course;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Repository.ICourseRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CourseRepository implements ICourseRepository {

    private List<Course> coursesList = new ArrayList<>();

    @Override
    public void addCourse(Course course) {
        coursesList.add(course);
    }

    @Override
    public void deleteCourse(Integer courseId) {
        coursesList.removeIf(course -> course.getCourseID().equals(courseId));
    }

    @Override
    public void updateCourse(Course course) {
        coursesList.remove(course);
        coursesList.add(course);
    }

    @Override
    public Optional<Course> getCourseById(Integer courseId) {
        return coursesList.stream()
                .filter(course -> course.getCourseID().equals(courseId))
                .findFirst();
    }

    @Override
    public List<Course> getAllCourses() {
        return coursesList;
    }
}
