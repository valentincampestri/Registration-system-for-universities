package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.utils;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.Request.CourseRequestDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.Request.InscriptionRequestDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.Response.CourseResponseDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.Response.FeeResponseDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.SubjectDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity.*;
import org.springframework.context.annotation.Configuration;

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
                course.getLastInscriptionDate(),
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
                courseResponseDto.getLastInscriptionDate(),
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
                courseRequestDto.getLastInscriptionDate(),
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
}
