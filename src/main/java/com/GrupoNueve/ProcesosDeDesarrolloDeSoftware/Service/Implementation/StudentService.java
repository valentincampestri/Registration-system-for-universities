package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Service.Implementation;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.Request.ProfessorRequestDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.Request.StudentRequestDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.Response.MessageResponseDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity.Professor;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity.Student;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity.Subject;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Exception.BadRequestException;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Repository.IStudentRepository;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Repository.ISubjectRepository;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Service.IStudentService;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.utils.Mapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService implements IStudentService {
    IStudentRepository studentRepository;

    ISubjectRepository subjectRepository;

    public StudentService(IStudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public MessageResponseDto createStudent(StudentRequestDto studentRequestDto) {
        if (studentRequestDto.getApprovedSubjectsCodeList() == null) {
            throw new BadRequestException("List of approved subjects is null.");
        }

        Optional<Student> existentStudent = studentRepository.
                getStudentByCode(studentRequestDto.getPersonID());

        if (existentStudent.isPresent()){
            throw new BadRequestException("Student already exists.");
        }

        List<Subject> subjects = new ArrayList<>();
        for (String subjectCode : studentRequestDto.getApprovedSubjectsCodeList()){
            Optional<Subject> subjectCandidate = subjectRepository.getSubjectByCode(subjectCode);
            if (subjectCandidate.isEmpty()) {
                throw new BadRequestException("One or more subject don't exists.");
            }
            subjects.add(subjectCandidate.get());
        }

        Student student = Mapper.convertStudentRequestDtoToStudent(studentRequestDto, subjects);

        studentRepository.addStudent(student);

        return new MessageResponseDto("Student created successfully.");
    }
}
