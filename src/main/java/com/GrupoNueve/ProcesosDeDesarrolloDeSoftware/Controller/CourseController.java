package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Controller;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.Request.CourseRequestDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.Response.CourseResponseDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.Response.MessageResponseDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.ScheduleDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Service.ICourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {
    ICourseService courseService;

    public CourseController(ICourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllCourses() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    @PostMapping("/create/{professorCode}/{subjectCode}")
    public ResponseEntity<?> createCourse(@RequestBody CourseRequestDto courseRequestDto, @PathVariable String professorCode, @PathVariable String subjectCode) {
        return ResponseEntity.ok(courseService.createCourse(courseRequestDto, professorCode, subjectCode));
    }
    @GetMapping("/subject/{subjectId}")
    public ResponseEntity<List<CourseResponseDto>> getCoursesBySubject(@PathVariable String subjectId) {
        return ResponseEntity.ok(courseService.getCoursesBySubject(subjectId));
    }

    @GetMapping("/professor/{professorId}")
    public ResponseEntity<List<CourseResponseDto>> getCoursesByProfessor(@PathVariable String professorId) {
        return ResponseEntity.ok(courseService.getCoursesByProfessor(professorId));
    }
    @GetMapping("/{courseId}/schedule")
    public ScheduleDto getScheduleByCourse(@PathVariable String courseId) {
        return courseService.getScheduleByCourse(courseId);
    }
    @PostMapping("/generate-report")
    public MessageResponseDto getTermReportByProfessor(@RequestParam("professorId") String professorId) {
        return courseService.getTermReportByProfessor(professorId);
    }
}
