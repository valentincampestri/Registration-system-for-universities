package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Controller;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.Request.CourseRequestDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.Response.CourseResponseDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.Response.MessageResponseDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.ScheduleDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Service.ICourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/subject/{subjectCode}")
    public ResponseEntity<List<CourseResponseDto>> getCoursesBySubject(@PathVariable String subjectCode) {
        return ResponseEntity.ok(courseService.getCoursesBySubject(subjectCode));
    }

    @GetMapping("/shift/{shift}")
    public ResponseEntity<List<CourseResponseDto>> getCoursesByShift(@PathVariable String shift) {
        return ResponseEntity.ok(courseService.getCoursesByShift(shift));
    }

    @GetMapping("/subject-shift/{subjectCode}/{shift}")
    public ResponseEntity<List<CourseResponseDto>> getCoursesBySubectAndShift(@PathVariable String subjectCode, @PathVariable String shift) {
        return ResponseEntity.ok(courseService.getCoursesBySubectAndShift(subjectCode, shift));
    }

    @GetMapping("/professor/{professorCode}")
    public ResponseEntity<List<CourseResponseDto>> getCoursesByProfessor(@PathVariable String professorCode) {
        return ResponseEntity.ok(courseService.getCoursesByProfessor(professorCode));
    }

    @GetMapping("/{courseCode}/schedule")
    public ScheduleDto getScheduleByCourse(@PathVariable String courseCode) {
        return courseService.getScheduleByCourse(courseCode);
    }

    @PostMapping("/generate-report")
    public MessageResponseDto getTermReportByProfessor(@RequestParam String professorCode, @RequestParam String reportFormat) {
        return courseService.getTermReportByProfessor(professorCode, reportFormat);
    }
}
