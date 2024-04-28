package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.utills;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.Request.CourseRequestDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.Request.ProfessorRequestDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.Request.StudentRequestDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.Response.CourseResponseDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.Response.InscriptionResponseDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.SubjectDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity.*;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class Mapper {
    public static CourseResponseDto convertCourseToCourseResponseDto(Course course) {
        return new CourseResponseDto(
                course.getCourseCode(),
                course.getStartTime(),
                course.getEndTime(),
                course.getModality(),
                course.getProfessor(),
                course.getSubject(),
                course.getClassroom(),
                course.getTerm(),
                course.getDaysList(),
                course.getSchedule(),
                course.getPrice()
        );
    }

    public static Course convertCourseResponseDtoToCourse(CourseResponseDto courseResponseDto) {
        return new Course(
                courseResponseDto.getCourseCode(),
                courseResponseDto.getStartTime(),
                courseResponseDto.getEndTime(),
                courseResponseDto.getModality(),
                courseResponseDto.getProfessor(),
                courseResponseDto.getSubject(),
                courseResponseDto.getClassroom(),
                courseResponseDto.getTerm(),
                courseResponseDto.getDaysList(),
                courseResponseDto.getSchedule(),
                courseResponseDto.getPrice()
        );
    }

    public static Course convertCourseRequestDtoToCourse(CourseRequestDto courseRequestDto, Professor professor, Subject subject) {
        return new Course(
                courseRequestDto.getCourseCode(),
                courseRequestDto.getStartTime(),
                courseRequestDto.getEndTime(),
                courseRequestDto.getModality(),
                professor,
                subject,
                courseRequestDto.getClassroom(),
                courseRequestDto.getTerm(),
                courseRequestDto.getDaysList(),
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
                subjectDto.getSubjectID(),
                subjectDto.getName(),
                subjectDto.getWorkload(),
                subjectDto.getPrerequisitesCodeList()
        );
    }

    public static InscriptionResponseDto convertInscriptionToInscriptionResponseDto(Inscription inscription) {
        return new InscriptionResponseDto(
                inscription.getInscriptionCode(),
                inscription.getStudent(),
                inscription.getCourse()
        );
    }

    public static Professor convertProfessorRequestDtoToProfessor(ProfessorRequestDto professorRequestDto,
                                                                  List<Subject> subjects){
        return new Professor(
                professorRequestDto.getName(),
                professorRequestDto.getLastName(),
                professorRequestDto.getPersonID(),
                professorRequestDto.getEmail(),
                professorRequestDto.getPhone(),
                professorRequestDto.getAddress(),
                subjects
        );
    }

    public static Student convertStudentRequestDtoToStudent(StudentRequestDto studentRequestDto,
                                                              List<Subject> subjects){
        return new Student(
                studentRequestDto.getName(),
                studentRequestDto.getLastName(),
                studentRequestDto.getPersonID(),
                studentRequestDto.getEmail(),
                studentRequestDto.getPhone(),
                studentRequestDto.getAddress(),
                studentRequestDto.getCareer(),
                subjects
        );
    }
}
