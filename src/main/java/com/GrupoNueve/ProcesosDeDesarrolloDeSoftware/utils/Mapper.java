package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.utils;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.dto.request.CourseRequestDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.dto.request.ProfessorRequestDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.dto.request.StudentRequestDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.dto.response.CourseResponseDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.dto.response.FeeResponseDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.dto.SubjectDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.entity.*;
import org.springframework.context.annotation.Configuration;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Configuration
public class Mapper {
    public static CourseResponseDto convertCourseToCourseResponseDto(Course course) {
        return new CourseResponseDto(
                course.getCourseCode(),
                course.getShift(),
                course.getModality(),
                course.getProfessor(),
                course.getSubject(),
                course.getClassroom(),
                course.getTerm(),
                course.getLastInscriptionDate(),
                course.getDaysList(),
                course.getSchedule(),
                course.getPrice()
        );
    }

    public static Course convertCourseResponseDtoToCourse(CourseResponseDto courseResponseDto) {
        return new Course(
                courseResponseDto.getCourseCode(),
                courseResponseDto.getShift(),
                courseResponseDto.getModality(),
                courseResponseDto.getProfessor(),
                courseResponseDto.getSubject(),
                courseResponseDto.getClassroom(),
                courseResponseDto.getTerm(),
                courseResponseDto.getLastInscriptionDate(),
                courseResponseDto.getDaysList(),
                courseResponseDto.getSchedule(),
                courseResponseDto.getPrice()
        );
    }

    public static Course convertCourseRequestDtoToCourse(CourseRequestDto courseRequestDto, Professor professor, Subject subject, List<DayOfWeek> dayOfWeek) {
        return new Course(
                courseRequestDto.getCourseCode(),
                courseRequestDto.getShift(),
                courseRequestDto.getModality(),
                professor,
                subject,
                courseRequestDto.getClassroom(),
                courseRequestDto.getTerm(),
                courseRequestDto.getLastInscriptionDate(),
                dayOfWeek,
                courseRequestDto.getSchedule(),
                courseRequestDto.getPrice()
        );
    }

    public static SubjectDto convertSubjectToSubjectDto(Subject subject) {
        return new SubjectDto(
                subject.getSubjectCode(),
                subject.getName(),
                subject.getWorkload(),
                subject.getPrerequisitesCodeList()
        );
    }

    public static Subject convertSubjectDtoToSubject(SubjectDto subjectDto) {
        return new Subject(
                subjectDto.getSubjectCode(),
                subjectDto.getName(),
                subjectDto.getWorkload(),
                subjectDto.getPrerequisitesCodeList()
        );
    }

    public static FeeResponseDto convertFeeToFeeResponseDto(Fee fee) {
        return new FeeResponseDto(
                fee.getFeeCode(),
                fee.getInscriptions(),
                fee.getPrice(),
                fee.getIsPaid()
        );
    }

    public static Professor convertProfessorRequestDtoToProfessor(ProfessorRequestDto professorRequestDto, List<Subject> subjects, Map<DayOfWeek, Set<Shift>> availability){
        return new Professor(
                professorRequestDto.getName(),
                professorRequestDto.getLastName(),
                professorRequestDto.getPersonID(),
                professorRequestDto.getEmail(),
                professorRequestDto.getPhone(),
                professorRequestDto.getAddress(),
                subjects,
                availability
        );
    }

    public static ProfessorRequestDto convertProfessorToProfessorRequestDto(Professor professor){
        Map<String, Set<String>> availability = professor.getAvailability().entrySet().stream().collect(Collectors.toMap(
                entry -> entry.getKey().toString(),
                entry -> entry.getValue().stream().map(Shift::toString).collect(Collectors.toSet())
        ));
        return new ProfessorRequestDto(
                professor.getPersonCode(),
                professor.getName(),
                professor.getLastName(),
                professor.getEmail(),
                professor.getPhone(),
                professor.getAddress(),
                professor.getSubjects().stream().map(Subject::getSubjectCode).toList(),
                availability

        );
    }

    public static Student convertStudentRequestDtoToStudent(StudentRequestDto studentRequestDto, List<Subject> subjects){
        return new Student(
                studentRequestDto.getName(),
                studentRequestDto.getLastName(),
                studentRequestDto.getPersonCode(),
                studentRequestDto.getEmail(),
                studentRequestDto.getPhone(),
                studentRequestDto.getAddress(),
                studentRequestDto.getCareer(),
                subjects
        );
    }

    public static StudentRequestDto convertStudentToStudentRequestDto(Student student){
        return new StudentRequestDto(
                student.getPersonCode(),
                student.getName(),
                student.getLastName(),
                student.getEmail(),
                student.getPhone(),
                student.getAddress(),
                student.getCareer(),
                student.getApprovedSubjectList().stream().map(Subject::getSubjectCode).toList()
        );
    }
}
