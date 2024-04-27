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

    @PostMapping("/create/{personId}/{subjectId}")
    public ResponseEntity<?> createCourse(@RequestBody CourseRequestDto courseRequestDto, @PathVariable String personId, @PathVariable String subjectId) {
        return ResponseEntity.ok(courseService.createCourse(courseRequestDto, personId, subjectId));
    }
    @GetMapping("/subject/{subjectId}")
    public ResponseEntity<List<CourseResponseDto>> getCoursesBySubject(@PathVariable String subjectId) {
        List<CourseResponseDto> courses = courseService.getCoursesBySubject(subjectId);
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/professor/{professorId}")
    public ResponseEntity<List<CourseResponseDto>> getCoursesByProfessor(@PathVariable String professorId) {
        List<CourseResponseDto> courses = courseService.getCoursesByProfessor(professorId);
        return ResponseEntity.ok(courses);
    }
    @GetMapping("/{courseId}/schedule")
    public ScheduleDto getScheduleByCourse(@PathVariable String courseId) {
        return courseService.getScheduleByCourse(courseId);
    }
    @PostMapping("/generate-report")
    public MessageResponseDto generateReport(@RequestParam("professorId") String professorId) {
        return courseService.generateReport(professorId);
    }
}
