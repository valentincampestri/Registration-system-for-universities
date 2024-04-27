package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.utills;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.Request.CourseRequestDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.Response.CourseResponseDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.SubjectDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity.Course;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity.Professor;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity.Subject;
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
                course.getSchedule(),
                course.getPrice()
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
                courseResponseDto.getSchedule(),
                courseResponseDto.getPrice()
        );
    }

    public static Course convertCourseRequestDtoToCourse(CourseRequestDto courseRequestDto, Professor professor, Subject subject) {
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
                courseRequestDto.getSchedule(),
                courseRequestDto.getPrice()
        );
    }

    public static SubjectDto convertSubjectToSubjectDto(Subject subject) {
        return new SubjectDto(
                subject.getSubjectID(),
                subject.getName(),
                subject.getWorkload(),
                subject.getPrerequisites()
        );
    }

    public static Subject convertSubjectDtoToSubject(SubjectDto subjectDto) {
        return new Subject(
                subjectDto.getSubjectID(),
                subjectDto.getName(),
                subjectDto.getWorkload(),
                subjectDto.getPrerequisites()
        );
    }
}
