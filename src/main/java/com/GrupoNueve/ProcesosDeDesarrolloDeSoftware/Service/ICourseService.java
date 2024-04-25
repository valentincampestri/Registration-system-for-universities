package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Service;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.CoursesDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.ReportDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.ScheduleDto;

import java.util.List;

public interface ICourseService {
    public void generateReport();
    public List<CoursesDto> getCoursesBySubject(Integer subjectId);
    public ScheduleDto getScheduleByCourse(Integer courseId);
    public List<CoursesDto> getCoursesByProfessor();
    public ReportDto getTermReportByProfessor(String professorId);
}
