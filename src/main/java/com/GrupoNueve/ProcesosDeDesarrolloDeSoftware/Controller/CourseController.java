package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Controller;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.CourseDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Service.ICourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/create")
    public ResponseEntity<?> createCourse(@RequestBody CourseDto courseDto) {
        return ResponseEntity.ok(courseService.createCourse(courseDto));
    }
}
