package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.controller;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.dto.ScheduleDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.dto.request.InscriptionRequestDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.dto.response.CourseResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<?> pay(@PathVariable String studentCode, @RequestParam String paymentMethod) {
        return feeController.pay(studentCode, paymentMethod);
    }

    @PostMapping("/inscription/create/{studentCode}/")
    public ResponseEntity<?> createInscription(InscriptionRequestDto inscriptionRequestDto, @PathVariable String studentCode) {
        return inscriptionController.createInscription(inscriptionRequestDto, studentCode);
    }
}
