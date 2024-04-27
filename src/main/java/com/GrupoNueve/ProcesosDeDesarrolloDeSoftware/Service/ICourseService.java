package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Service;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.Request.CourseRequestDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.Response.CourseResponseDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.Response.MessageResponseDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.ScheduleDto;

import java.util.List;

public interface ICourseService {
    MessageResponseDto generateReport(String professorId);

    List<CourseResponseDto> getCoursesBySubject(String subjectId);

    ScheduleDto getScheduleByCourse(String courseId);

    List<CourseResponseDto> getCoursesByProfessor(String professorId);

    void getTermReportByProfessor(String professorId);

    List<CourseResponseDto> getAllCourses();

    MessageResponseDto createCourse(CourseRequestDto courseRequestDto, String personId, String subjectId);
}
