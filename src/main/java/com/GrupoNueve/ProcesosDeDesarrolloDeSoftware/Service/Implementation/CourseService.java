package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Service.Implementation;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.CoursesDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.ReportDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.ScheduleDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Repository.ICourseRepository;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Service.ICourseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService implements ICourseService {
    ICourseRepository courseRepository;

    public CourseService(ICourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public void generateReport() {

    }

    @Override
    public List<CoursesDto> getCoursesBySubject(Integer subjectId) {
        return null;
    }

    @Override
    public ScheduleDto getScheduleByCourse(Integer courseId) {
        return null;
    }

    @Override
    public List<CoursesDto> getCoursesByProfessor() {
        return null;
    }

    @Override
    public ReportDto getTermReportByProfessor(String professorId) {
        return null;
    }
}
