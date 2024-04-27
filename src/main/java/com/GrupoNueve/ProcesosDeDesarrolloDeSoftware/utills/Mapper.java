package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.utills;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.Request.CourseRequestDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.Response.CourseResponseDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity.*;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Mapper {
    public static CourseResponseDto convertCourseToCourseResponseDto(Course course) {
        return new CourseResponseDto(
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
        );
    }

    public static Course convertCourseResponseDtoToCourse(CourseResponseDto courseResponseDto) {
        return new Course(
                courseResponseDto.getCourseID(),
                courseResponseDto.getStartTime(),
                courseResponseDto.getEndTime(),
                courseResponseDto.getModality(),
                courseResponseDto.getProfessor(),
                courseResponseDto.getSubject(),
                courseResponseDto.getClassroom(),
                courseResponseDto.getTerm(),
                courseResponseDto.getDaysList(),
                courseResponseDto.getSchedule()
        );
    }

    public static Course convertCourseRequestDtoToCourse(CourseRequestDto courseRequestDto, Professor professor, Subject subject){
        return new Course(
                courseRequestDto.getCourseID(),
                courseRequestDto.getStartTime(),
                courseRequestDto.getEndTime(),
                courseRequestDto.getModality(),
                professor,
                subject,
                courseRequestDto.getClassroom(),
                courseRequestDto.getTerm(),
                courseRequestDto.getDaysList(),
                courseRequestDto.getSchedule()
        );
    }
}
