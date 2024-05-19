package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Service.Implementation;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.Request.InscriptionRequestDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.Response.MessageResponseDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity.*;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Exception.BadRequestException;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Exception.NotFoundException;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Repository.ICourseRepository;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Repository.IInscriptionRepository;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Repository.IStudentRepository;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Service.IInscriptionService;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.utils.Utils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class InscriptionService implements IInscriptionService {
    IInscriptionRepository inscriptionRepository;
    Utils utils;
    IStudentRepository studentRepository;
    ICourseRepository courseRepository;

    public InscriptionService(IInscriptionRepository inscriptionRepository, Utils utils, IStudentRepository studentRepository, ICourseRepository courseRepository) {
        this.inscriptionRepository = inscriptionRepository;
        this.utils = utils;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public MessageResponseDto createInscription(InscriptionRequestDto inscriptionRequestDto, String studentCode) {
        Optional<Student> existentStudent = studentRepository.getStudentByCode(studentCode);
        if (existentStudent.isEmpty()) {
            throw new NotFoundException("Student does not exist.");
        }
        List<Course> courseList = new ArrayList<>();
        for (String courseCode : inscriptionRequestDto.getCoursesCodeList()) {
            Optional<Course> existentCourse = courseRepository.getCourseByCode(courseCode);
            if (existentCourse.isEmpty()) {
                throw new NotFoundException("Course does not exist.");
            }
            courseList.add(existentCourse.get());
        }
        Inscription inscription = new Inscription(existentStudent.get(), courseList);
        inscriptionChecks(inscription);
        inscriptionMaxCapacityCheck(inscription);
        inscriptionRepository.addInscription(inscription);
        utils.addFee(inscription);
        return new MessageResponseDto("You have successfully enrolled in the selected courses. Now you can pay according to your student code.");
    }

    private void inscriptionChecks(Inscription inscription) {
        boolean okPrerequisites;
        List<Inscription> existentInscriptions = inscriptionRepository.getInscriptionByStudentCode(inscription.getStudent().getPersonCode());

        HashSet<String> necessaryPrerequisites = new HashSet<>();
        HashSet<String> approvedSubjects = new HashSet<>();
        Student student = inscription.getStudent();
        Career career;
        int maxWorkLoad = 0;
        if (student != null && student.getCareer() != null) {
            //workload
            career = student.getCareer();
            if (career.getMaxWorkload() != null) maxWorkLoad = career.getMaxWorkload();

            //prerequisites
            if (!student.getApprovedSubjectList().isEmpty()) {
                for (Subject subject : student.getApprovedSubjectList()) {
                    if (subject.getSubjectCode() != null) {
                        approvedSubjects.add(subject.getSubjectCode());
                    }
                }
            }
        }
        //maxWorkLoad retrieved

        List<Course> courses = inscription.getCourses();
        int currentWorkLoad = 0;
        Subject subject;
        //aguego lastInscriptionDate al Course Entity.
        LocalDate lastInscriptionDate;
        LocalDate today = LocalDate.now();
        if (!courses.isEmpty()) {
            for (Course course : courses) {
                for (Inscription eachInscription : existentInscriptions) {
                    for (Course eachCourseDataBase : eachInscription.getCourses()) {
                        if (eachCourseDataBase.getSubject().getSubjectCode().equals(course.getSubject().getSubjectCode())
                                && eachCourseDataBase.getTerm().equals(course.getTerm())) {
                            throw new BadRequestException("You are already enrolled in one or more subjects that you are trying to enroll in this term.");
                        }
                    }

                    subject = course.getSubject();
                    //workload
                    if (subject != null && subject.getWorkload() != null) currentWorkLoad += subject.getWorkload();

                    //inscriptiondate
                    lastInscriptionDate = course.getLastInscriptionDate();
                    if (lastInscriptionDate != null && today.isAfter(lastInscriptionDate)) {
                        throw new BadRequestException("The inscription date has expired for one or more subjects that you are trying to enroll in this term.");
                    }

                    //prerequisites
                    subject = course.getSubject();
                    if (!subject.getPrerequisitesCodeList().isEmpty()) {
                        necessaryPrerequisites.addAll(subject.getPrerequisitesCodeList());
                    }
                    if (approvedSubjects.contains(subject.getSubjectCode())) {
                        throw new BadRequestException("You are already approved in one or more subjects that you are trying to enroll in this term.");
                    }
                }
            }

            if (currentWorkLoad > maxWorkLoad) {
                throw new BadRequestException("The workload exceeds the maximum workload allowed for the career.");
            }

            okPrerequisites = approvedSubjects.containsAll(necessaryPrerequisites);
            if (!okPrerequisites) {
                throw new BadRequestException("You need to pass one or more subjects as a prerequisite to enroll in one or more subjects that you are trying to enroll in this term.");
            }
        }
    }

    private void inscriptionMaxCapacityCheck(Inscription inscription) {
        for (Course course : inscription.getCourses()) {
            if (course.getClassroom().getMaxCapacity() <= inscriptionRepository.getInscriptionsByCourseCode(course.getCourseCode()).size()) {
                throw new BadRequestException("The course " + course.getCourseCode() + " is already full.");
            }
        }
    }
}