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
import java.util.stream.Collectors;

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
    public List<CourseResponseDto> getCoursesBySubject(String subjectId) {

        List<Course> allCourses = courseRepository.getAllCourses();
        if (allCourses.isEmpty()) {
            throw new NotFoundException("There are no courses.");
        }

        List<Course> coursesFilteredBySubject = allCourses.stream()
                .filter(course -> course.getSubject().getSubjectCode().equals(subjectId))
                .collect(Collectors.toList());


        List<CourseResponseDto> responseDtos = coursesFilteredBySubject.stream()
                .map(Mapper::convertCourseToCourseResponseDto)
                .collect(Collectors.toList());

        return responseDtos;
    }

    @Override
    public ScheduleDto getScheduleByCourse(String courseId) {

        Course course = courseRepository.getCourseByCode(courseId)
                .orElseThrow(() -> new NotFoundException("Course not found with ID: " + courseId));


        String schedule = course.getSchedule();


        ScheduleDto scheduleDto = new ScheduleDto();
        scheduleDto.setSchedule(schedule);

        return scheduleDto;
    }

    @Override
    public List<CourseResponseDto> getCoursesByProfessor(String professorId) {

        List<Course> allCourses = courseRepository.getAllCourses();

        if (allCourses.isEmpty()) {
            throw new NotFoundException("There are no courses.");
        }
        List<Course> coursesFilteredByProfessor = allCourses.stream()
                .filter(course -> course.getProfessor().getPersonCode().equals(professorId))
                .collect(Collectors.toList());

        // Mapear los cursos filtrados al DTO de respuesta
        List<CourseResponseDto> responseDtos = coursesFilteredByProfessor.stream()
                .map(Mapper::convertCourseToCourseResponseDto)
                .collect(Collectors.toList());

        return responseDtos;
    }

    @Override
    public MessageResponseDto getTermReportByProfessor(String professorId) {
        List<Course> courses = courseRepository.getCoursesByProfessor(professorId);


        if (courses.isEmpty()) {
            return new MessageResponseDto("No hay cursos asignados para el docente con ID: " + professorId);
        }


        StringBuilder reportContent = new StringBuilder();
        reportContent.append("Informe de cursos asignados para el docente con ID: ").append(professorId).append("\n\n");
        for (Course course : courses) {
            reportContent.append("Nombre del curso: ").append(course.getSubject().getName()).append("\n");
            reportContent.append("Horario: ").append(course.getStartTime()).append(" - ").append(course.getEndTime()).append("\n");
            reportContent.append("Aula asignada: ").append(course.getClassroom().getClassroomCode()).append("\n\n");
        }

        return new MessageResponseDto("Generando reporte en PDF para el docente con ID: " + professorId + "...");

    }

    @Override
    public MessageResponseDto createCourse(CourseRequestDto courseRequestDto, String professorId, String subjectId) {
        Optional<Professor> existentProfessor = professorRepository.getProfessorByCode(professorId);
        if (existentProfessor.isEmpty()) {
            throw new NotFoundException("Professor does not exist.");
        }
        Optional<Subject> existentSubject = subjectRepository.getSubjectByCode(subjectId);
        if (existentSubject.isEmpty()) {
            throw new NotFoundException("Subject does not exist.");
        }
        Course course = Mapper.convertCourseRequestDtoToCourse(courseRequestDto, existentProfessor.get(), existentSubject.get());
        Optional<Course> existentCourse = courseRepository.getCourseByCode(course.getCourseCode());
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
