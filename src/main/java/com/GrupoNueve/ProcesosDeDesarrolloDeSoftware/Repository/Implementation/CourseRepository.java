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
    public void deleteCourse(String courseId) {
        coursesList.removeIf(course -> course.getCourseCode().equals(courseId));
    }

    @Override
    public void updateCourse(Course course) {
        coursesList.remove(course);
        coursesList.add(course);
    }

    @Override
    public Optional<Course> getCourseByCode(String courseId) {
        return coursesList.stream()
                .filter(course -> course.getCourseCode().equals(courseId))
                .findFirst();
    }

    @Override
    public List<Course> getAllCourses() {
        return coursesList;
    }
}
