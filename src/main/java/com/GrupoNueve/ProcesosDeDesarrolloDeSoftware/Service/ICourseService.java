package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Service;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.Request.CourseRequestDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.Response.CourseResponseDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.Response.MessageResponseDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.ScheduleDto;

import java.util.List;

public interface ICourseService {

    List<CourseResponseDto> getAllCourses();

    MessageResponseDto createCourse(CourseRequestDto courseRequestDto, String personId, String subjectCode);

    List<CourseResponseDto> getCoursesBySubject(String subjectCode);

    List<CourseResponseDto> getCoursesByShift(String shift);

    List<CourseResponseDto> getCoursesBySubectAndShift(String subjectCode, String shift);

    List<CourseResponseDto> getCoursesByProfessor(String professorCode);

    ScheduleDto getScheduleByCourse(String courseCode);

    MessageResponseDto getTermReportByProfessor(String professorCode);
}
