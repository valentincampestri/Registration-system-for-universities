package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Service;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.CourseDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.MessageResponse;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.ReportDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.ScheduleDto;

import java.util.List;

public interface ICourseService {
    void generateReport();
    List<CourseDto> getCoursesBySubject(Integer subjectId);
    ScheduleDto getScheduleByCourse(Integer courseId);
    List<CourseDto> getCoursesByProfessor();
    ReportDto getTermReportByProfessor(String professorId);

    List<CourseDto> getAllCourses();

    MessageResponse createCourse(CourseDto courseDto);
}
