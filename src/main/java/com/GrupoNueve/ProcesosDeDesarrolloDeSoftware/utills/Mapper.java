package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.utills;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.CourseDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity.*;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Mapper {
    public static CourseDto convertCourseToCourseDto(Course course) {
        return new CourseDto(
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

    public static Course convertCourseDtoToCourse(CourseDto courseDto) {
        return new Course(
                courseDto.getCourseID(),
                courseDto.getStartTime(),
                courseDto.getEndTime(),
                courseDto.getModality(),
                courseDto.getProfessor(),
                courseDto.getSubject(),
                courseDto.getClassroom(),
                courseDto.getTerm(),
                courseDto.getDaysList(),
                courseDto.getSchedule()
        );
    }
}
