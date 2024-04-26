package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Service.Implementation;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.CourseDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.MessageResponse;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.ReportDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.ScheduleDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity.*;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Exception.NotFoundException;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Repository.ICourseRepository;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Service.ICourseService;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.utills.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService implements ICourseService {
    ICourseRepository courseRepository;

    public CourseService(ICourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public void generateReport() {

    }

    @Override
    public List<CourseDto> getCoursesBySubject(Integer subjectId) {
        return null;
    }

    @Override
    public ScheduleDto getScheduleByCourse(Integer courseId) {
        return null;
    }

    @Override
    public List<CourseDto> getCoursesByProfessor() {
        return null;
    }

    @Override
    public ReportDto getTermReportByProfessor(String professorId) {
        return null;
    }

    @Override
    public MessageResponse createCourse(CourseDto courseDto) {
        Course course = Mapper.convertCourseDtoToCourse(courseDto);
        Optional<Course> existentCourse = courseRepository.getCourseById(course.getCourseID());
        if (existentCourse.isPresent()) {
            throw new NotFoundException("Course already exists.");
        } else {
            courseRepository.addCourse(course);
        }
        return new MessageResponse("Course created successfully.");
    }

    @Override
    public List<CourseDto> getAllCourses() {
        List<Course> courseList = courseRepository.getAllCourses();
        if (courseList.isEmpty()) {
            throw new NotFoundException("No courses found.");
        }
        List<CourseDto> response = courseList.stream()
                .map(course -> new CourseDto(
                        course.getCourseID(),
                        course.getStartTime(),
                        course.getEndTime(),
                        course.getModality(),
                        course.getProfessor(),
                        course.getSubject(),
                        course.getClassroom(),
                        course.getTerm(),
                        course.getDaysList(),
                        course.getSchedule()
                ))
                .toList();
        return response;
    }
}
