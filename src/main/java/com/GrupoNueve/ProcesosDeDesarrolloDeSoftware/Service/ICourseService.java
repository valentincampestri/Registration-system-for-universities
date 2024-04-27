package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Service;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.MessageResponseDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.Request.CourseRequestDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.Response.CourseResponseDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.ScheduleDto;

import java.util.List;

public interface ICourseService {
    void generateReport();
    List<CourseResponseDto> getCoursesBySubject(Integer subjectId);
    ScheduleDto getScheduleByCourse(Integer courseId);
    List<CourseResponseDto> getCoursesByProfessor();
    void getTermReportByProfessor(String professorId);

    List<CourseResponseDto> getAllCourses();

    MessageResponseDto createCourse(CourseRequestDto courseRequestDto, String personId, String subjectId);
}
