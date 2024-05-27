package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.service.implementation;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.dto.request.InscriptionRequestDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.dto.response.MessageResponseDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.entity.Course;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.entity.Inscription;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.entity.Student;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.exception.NotFoundException;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.repository.ICourseRepository;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.repository.IInscriptionRepository;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.repository.IStudentRepository;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.service.IInscriptionCheckStrategy;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.service.IInscriptionService;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.service.implementation.strategies.AlreadyApprovedSubjectCheckStrategy;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.service.implementation.strategies.AlreadyEnrolledCheckStrategy;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.service.implementation.strategies.InscriptionDateCheckStrategy;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.service.implementation.strategies.MaxCapacityCheckStrategy;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.service.implementation.strategies.PrerequisitesCheckStrategy;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.service.implementation.strategies.WorkloadCheckStrategy;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.utils.Utils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        List<IInscriptionCheckStrategy> checkStrategies = List.of(
                new WorkloadCheckStrategy(),
                new PrerequisitesCheckStrategy(),
                new InscriptionDateCheckStrategy(),
                new AlreadyEnrolledCheckStrategy(inscriptionRepository),
                new AlreadyApprovedSubjectCheckStrategy(),
                new MaxCapacityCheckStrategy(inscriptionRepository)
        );
        for (IInscriptionCheckStrategy checkStrategy : checkStrategies) {
            checkStrategy.check(inscription);
        }
        inscriptionRepository.addInscription(inscription);
        utils.addFee(inscription);
        return new MessageResponseDto("You have successfully enrolled in the selected courses. Now you can pay according to your student code.");
    }
}