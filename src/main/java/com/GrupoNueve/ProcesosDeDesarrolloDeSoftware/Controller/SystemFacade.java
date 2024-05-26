package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Controller;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.Request.InscriptionRequestDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.Response.CourseResponseDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.ScheduleDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/system")
public class SystemFacade {
    CourseController courseController;
    FeeController feeController;
    InscriptionController inscriptionController;

    public SystemFacade(CourseController courseController, FeeController feeController, InscriptionController inscriptionController) {
        this.courseController = courseController;
        this.feeController = feeController;
        this.inscriptionController = inscriptionController;
    }
    @GetMapping("/course/subject/{subjectCode}")
    public ResponseEntity<List<CourseResponseDto>> getCoursesBySubject(@PathVariable String subjectCode) {
        return courseController.getCoursesBySubject(subjectCode);
    }

    @GetMapping("/course/shift/{shift}")
    public ResponseEntity<List<CourseResponseDto>> getCoursesByShift(@PathVariable String shift) {
        return courseController.getCoursesByShift(shift);
    }

    @GetMapping("/course/subject-shift/{subjectCode}/{shift}")
    public ResponseEntity<List<CourseResponseDto>> getCoursesBySubectAndShift(@PathVariable String subjectCode, @PathVariable String shift) {
        return courseController.getCoursesBySubectAndShift(subjectCode, shift);
    }

    @GetMapping("/course/professor/{professorCode}")
    public ResponseEntity<List<CourseResponseDto>> getCoursesByProfessor(@PathVariable String professorCode) {
        return courseController.getCoursesByProfessor(professorCode);
    }

    @GetMapping("/course/{courseCode}/schedule")
    public ScheduleDto getScheduleByCourse(@PathVariable String courseCode) {
        return courseController.getScheduleByCourse(courseCode);
    }

    @GetMapping("/fee/{studentCode}")
    public ResponseEntity<?> getFeesByStudent(@PathVariable String studentCode) {
        return feeController.getFeesByStudent(studentCode);
    }

    @PostMapping("/fee/pay/{studentCode}")
    public ResponseEntity<?> pay (@PathVariable String studentCode, @RequestParam String paymentMethod) {
        return feeController.pay(studentCode, paymentMethod);
    }

    @PostMapping("/inscription/create/{studentCode}/")
    public ResponseEntity<?> createInscription(InscriptionRequestDto inscriptionRequestDto, @PathVariable String studentCode) {
        return inscriptionController.createInscription(inscriptionRequestDto, studentCode);
    }
}
