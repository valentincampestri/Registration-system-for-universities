package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.service;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.dto.ScheduleDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.dto.request.CourseRequestDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.dto.response.CourseResponseDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.dto.response.MessageResponseDto;

import java.util.List;

public interface ICourseService {

    List<CourseResponseDto> getAllCourses();

    MessageResponseDto createCourse(CourseRequestDto courseRequestDto, String subjectCode);

    List<CourseResponseDto> getCoursesBySubject(String subjectCode);

    List<CourseResponseDto> getCoursesByShift(String shift);

    List<CourseResponseDto> getCoursesBySubectAndShift(String subjectCode, String shift);

    List<CourseResponseDto> getCoursesByProfessor(String professorCode);

    ScheduleDto getScheduleByCourse(String courseCode);

    MessageResponseDto getTermReportByProfessor(String professorCode, String reportFormat, String termCode);
}
