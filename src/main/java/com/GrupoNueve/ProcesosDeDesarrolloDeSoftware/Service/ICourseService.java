package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Service;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.Request.CourseRequestDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.Response.CourseResponseDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.Response.MessageResponseDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.ScheduleDto;

import java.util.List;

public interface ICourseService {

    List<CourseResponseDto> getCoursesBySubject(String subjectCode);

    ScheduleDto getScheduleByCourse(String courseCode);

    List<CourseResponseDto> getCoursesByProfessor(String professorCode);

    MessageResponseDto getTermReportByProfessor(String professorCode);

    List<CourseResponseDto> getAllCourses();

    MessageResponseDto createCourse(CourseRequestDto courseRequestDto, String personId, String subjectId);
}
