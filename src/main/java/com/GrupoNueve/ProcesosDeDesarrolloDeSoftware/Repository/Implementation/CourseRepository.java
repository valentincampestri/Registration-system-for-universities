package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Repository.Implementation;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity.Course;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Repository.ICourseRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CourseRepository implements ICourseRepository {

    private List<Course> coursesList = new ArrayList<>();

    /*public CourseRepository() throws IOException {
        loadCourses();
    }*/

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

    /*private void loadCourses() throws IOException {
        File file;
        ObjectMapper objectMapper = new ObjectMapper();
        List<Course> mappedCourses;

        try {
            file = ResourceUtils.getFile("classpath:Course.json");

            objectMapper.registerModule(new JavaTimeModule());
            objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            objectMapper.setDateFormat(new SimpleDateFormat("dd-MM-yyyy"));
            mappedCourses = objectMapper.readValue(file, new TypeReference<List<Course>>() {
            });
            coursesList = mappedCourses;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println(LocalDate.now());
        }
    }*/
}
