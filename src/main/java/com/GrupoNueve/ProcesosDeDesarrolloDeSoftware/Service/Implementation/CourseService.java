package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Service.Implementation;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.Request.CourseRequestDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.Response.CourseResponseDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.Response.MessageResponseDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.ScheduleDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity.Course;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity.Professor;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity.Subject;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Exception.BadRequestException;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Exception.NotFoundException;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Repository.ICourseRepository;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Repository.IProfessorRepository;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Repository.ISubjectRepository;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Service.ICourseService;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.utills.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService implements ICourseService {
    ICourseRepository courseRepository;
    IProfessorRepository professorRepository;
    ISubjectRepository subjectRepository;

    public CourseService(ICourseRepository courseRepository, IProfessorRepository professorRepository, ISubjectRepository subjectRepository) {
        this.courseRepository = courseRepository;
        this.professorRepository = professorRepository;
        this.subjectRepository = subjectRepository;
    }

    @Override
    public void generateReport() {
    }

    @Override
    public List<CourseResponseDto> getCoursesBySubject(Integer subjectId) {
        return null;
    }

    @Override
    public ScheduleDto getScheduleByCourse(Integer courseId) {
        return null;
    }

    @Override
    public List<CourseResponseDto> getCoursesByProfessor() {
        return null;
    }

    @Override
    public void getTermReportByProfessor(String professorId) {
        return;
    }

    @Override
    public MessageResponseDto createCourse(CourseRequestDto courseRequestDto, String professorId, String subjectId) {
        Optional<Professor> existentProfessor = professorRepository.getProfessorById(professorId);
        if (!existentProfessor.isPresent()) {
            throw new NotFoundException("Professor does not exist.");
        }
        Optional<Subject> existentSubject = subjectRepository.getSubjectById(subjectId);
        if (!existentSubject.isPresent()) {
            throw new NotFoundException("Subject does not exist.");
        }
        Course course = Mapper.convertCourseRequestDtoToCourse(courseRequestDto, existentProfessor.get(), existentSubject.get());
        Optional<Course> existentCourse = courseRepository.getCourseById(course.getCourseID());
        if (existentCourse.isPresent()) {
            throw new BadRequestException("Course already exists.");
        } else {
            courseRepository.addCourse(course);
        }
        return new MessageResponseDto("Course created successfully.");
    }

    @Override
    public List<CourseResponseDto> getAllCourses() {
        List<Course> courseList = courseRepository.getAllCourses();
        if (courseList.isEmpty()) {
            throw new NotFoundException("There are no courses.");
        }
        List<CourseResponseDto> response = courseList.stream().map(Mapper::convertCourseToCourseResponseDto).toList();
        return response;
    }
}
