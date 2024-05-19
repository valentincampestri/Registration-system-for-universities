package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Service.Implementation;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.Request.CourseRequestDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.Response.CourseResponseDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.Response.MessageResponseDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.ScheduleDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity.*;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Exception.BadRequestException;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Exception.NotFoundException;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Repository.ICourseRepository;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Repository.IProfessorRepository;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Repository.ISubjectRepository;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Service.ICourseService;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.utils.Mapper;
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
    public List<CourseResponseDto> getAllCourses() {
        List<Course> courseList = courseRepository.getAllCourses();
        if (courseList.isEmpty()) {
            throw new NotFoundException("There are no courses.");
        }
        return courseList.stream().map(Mapper::convertCourseToCourseResponseDto).toList();
    }

    @Override
    public MessageResponseDto createCourse(CourseRequestDto courseRequestDto, String professorCode, String subjectCode) {
        Optional<Professor> existentProfessor = professorRepository.getProfessorByCode(professorCode);
        if (existentProfessor.isEmpty()) {
            throw new NotFoundException("Professor does not exist.");
        }
        Optional<Subject> existentSubject = subjectRepository.getSubjectByCode(subjectCode);
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
    public List<CourseResponseDto> getCoursesBySubject(String subjectCode) {
        List<Course> allCourses = courseRepository.getAllCourses();
        if (allCourses.isEmpty()) {
            throw new NotFoundException("There are no courses.");
        }

        Optional<Subject> existentSubject = subjectRepository.getSubjectByCode(subjectCode);
        if (existentSubject.isEmpty()) {
            throw new NotFoundException("Subject does not exist.");
        }

        List<Course> coursesFilteredBySubject = allCourses.stream()
                .filter(course -> course.getSubject().getSubjectCode().equals(subjectCode))
                .toList();


        return coursesFilteredBySubject.stream()
                .map(Mapper::convertCourseToCourseResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CourseResponseDto> getCoursesByShift(String shift) {
        if (!shift.equalsIgnoreCase("MORNING") && !shift.equalsIgnoreCase("AFTERNOON") && !shift.equalsIgnoreCase("NIGHT")){
            throw new BadRequestException("Invalid shift.");
        }

        List<Course> allCourses = courseRepository.getAllCourses();
        if (allCourses.isEmpty()) {
            throw new NotFoundException("There are no courses.");
        }

        Shift shiftEnum = Shift.valueOf(shift.toUpperCase());

        List<Course> coursesFilteredByShift = allCourses.stream()
                .filter(course -> course.getShift().equals(shiftEnum))
                .toList();

        return coursesFilteredByShift.stream()
                .map(Mapper::convertCourseToCourseResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CourseResponseDto> getCoursesBySubectAndShift(String subjectCode, String shift) {
        List<CourseResponseDto> coursesBySubject = getCoursesBySubject(subjectCode);
        List<CourseResponseDto> coursesByShift = getCoursesByShift(shift);
        List<CourseResponseDto> common = coursesBySubject.stream()
                .filter(coursesByShift::contains)
                .toList();

        if (common.isEmpty()) {
            throw new NotFoundException("There are no courses with the specified subject and shift.");
        }

        return common;
    }

    @Override
    public List<CourseResponseDto> getCoursesByProfessor(String professorCode) {
        List<Course> allCourses = courseRepository.getAllCourses();

        if (allCourses.isEmpty()) {
            throw new NotFoundException("There are no courses.");
        }
        List<Course> coursesFilteredByProfessor = allCourses.stream()
                .filter(course -> course.getProfessor().getPersonCode().equals(professorCode))
                .toList();

        return coursesFilteredByProfessor.stream()
                .map(Mapper::convertCourseToCourseResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public ScheduleDto getScheduleByCourse(String courseCode) {
        Course course = courseRepository.getCourseByCode(courseCode)
                .orElseThrow(() -> new NotFoundException("Course not found with ID: " + courseCode));

        String schedule = course.getSchedule();


        ScheduleDto scheduleDto = new ScheduleDto();
        scheduleDto.setSchedule(schedule);

        return scheduleDto;
    }

    @Override
    public MessageResponseDto getTermReportByProfessor(String professorCode) {
        List<Course> courses = courseRepository.getCoursesByProfessor(professorCode);

        if (courses.isEmpty()) {
            throw new BadRequestException("There are no courses assigned for the professor with ID: " + professorCode);
        }

        ReportPDF report = new ReportPDF();
        report.generateReport(courses);
        return new MessageResponseDto("PDF generated.");
    }
}
