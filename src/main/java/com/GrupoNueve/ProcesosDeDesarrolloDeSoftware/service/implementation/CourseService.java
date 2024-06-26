package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.service.implementation;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.dto.ScheduleDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.dto.request.CourseRequestDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.dto.response.CourseResponseDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.dto.response.MessageResponseDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.entity.Course;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.entity.IReport;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.entity.Professor;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.entity.ReportFactoryMethod;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.entity.Shift;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.entity.Subject;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.exception.BadRequestException;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.exception.NotFoundException;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.repository.ICourseRepository;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.repository.IInscriptionRepository;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.repository.IProfessorRepository;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.repository.ISubjectRepository;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.service.ICourseService;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.utils.Mapper;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class CourseService implements ICourseService {
    ICourseRepository courseRepository;
    IProfessorRepository professorRepository;
    ISubjectRepository subjectRepository;
    IInscriptionRepository inscriptionRepository;

    public CourseService(ICourseRepository courseRepository, IProfessorRepository professorRepository, ISubjectRepository subjectRepository, IInscriptionRepository inscriptionRepository) {
        this.courseRepository = courseRepository;
        this.professorRepository = professorRepository;
        this.subjectRepository = subjectRepository;
        this.inscriptionRepository = inscriptionRepository;
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
    public MessageResponseDto createCourse(CourseRequestDto courseRequestDto, String subjectCode) {
        List<DayOfWeek> daysList = new ArrayList<>();
        for (String day : courseRequestDto.getDaysList()) {
            if (!day.equalsIgnoreCase("MONDAY") && !day.equalsIgnoreCase("TUESDAY") &&
                    !day.equalsIgnoreCase("WEDNESDAY") && !day.equalsIgnoreCase("THURSDAY") &&
                    !day.equalsIgnoreCase("FRIDAY") && !day.equalsIgnoreCase("SATURDAY") &&
                    !day.equalsIgnoreCase("SUNDAY")) {
                throw new BadRequestException("Invalid day of week.");
            }
            daysList.add(DayOfWeek.valueOf(day.toUpperCase()));
        }

        if (courseRequestDto.getClassroom().getMaxCapacity() <= 10 || courseRequestDto.getClassroom().getMaxCapacity() >= 150) {
            throw new BadRequestException("Invalid classroom capacity, it must be from 10 to 150.");
        }

        Optional<Subject> existentSubject = subjectRepository.getSubjectByCode(subjectCode);
        if (existentSubject.isEmpty()) {
            throw new NotFoundException("Subject does not exist.");
        }

        List<Professor> allProfessors = professorRepository.getAllProfessors();
        List<Professor> assignedProfessor = new ArrayList<>();
        List<Course> allCourses = courseRepository.getAllCourses();
        for (Professor professor : allProfessors) {
            if (professor.getSubjects().contains(existentSubject.get())) {
                boolean isAvailable = true;
                for (DayOfWeek day : daysList) {
                    if (!professor.getAvailability().containsKey(day) ||
                            !professor.getAvailability().get(day).contains(courseRequestDto.getShift()) ||
                            allCourses.stream().anyMatch(course -> course.getProfessor().equals(professor) &&
                                    course.getDaysList().contains(day) &&
                                    course.getShift() == courseRequestDto.getShift())) {
                        isAvailable = false;
                        break;
                    }
                }
                if (isAvailable) {
                    assignedProfessor.add(professor);
                }
            }
        }

        if (assignedProfessor.isEmpty()) {
            throw new NotFoundException("There are no professors available for the specified subject and shift.");
        }

        Course course = Mapper.convertCourseRequestDtoToCourse(courseRequestDto, assignedProfessor.get(new Random().nextInt(assignedProfessor.size())), existentSubject.get(), daysList);
        Optional<Course> existentCourse = allCourses.stream()
                .filter(c -> c.getCourseCode().equals(course.getCourseCode()))
                .findAny();
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
        if (!shift.equalsIgnoreCase("MORNING") && !shift.equalsIgnoreCase("AFTERNOON") && !shift.equalsIgnoreCase("NIGHT")) {
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
    public MessageResponseDto getTermReportByProfessor(String professorCode, String reportFormat, String termCode) {
        Optional<Professor> existentProfessor = professorRepository.getProfessorByCode(professorCode);
        if (existentProfessor.isEmpty()) {
            throw new NotFoundException("Professor does not exist.");
        }

        List<Course> courses = courseRepository.getCoursesByProfessor(professorCode);
        courses = courses.stream()
                .filter(course -> course.getTerm().getTermCode().equals(termCode))
                .toList();
        if (courses.isEmpty()) {
            throw new BadRequestException("There are no courses assigned for the professor with ID: " + professorCode + " in the term with ID: " + termCode);
        }


        IReport report = ReportFactoryMethod.createReport(reportFormat, inscriptionRepository);
        report.generateReport(courses);

        return new MessageResponseDto("Term Report generated.");
    }
}
