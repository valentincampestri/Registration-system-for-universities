package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Service.Implementation;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.Request.ProfessorRequestDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.Response.MessageResponseDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity.Professor;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity.Subject;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Exception.BadRequestException;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Repository.IProfessorRepository;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Repository.ISubjectRepository;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Service.IProfessorService;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.utils.Mapper;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService implements IProfessorService {
    IProfessorRepository professorRepository;

    ISubjectRepository subjectRepository;

    public ProfessorService(IProfessorRepository professorRepository, ISubjectRepository subjectRepository) {
        this.professorRepository = professorRepository;
        this.subjectRepository = subjectRepository;
    }

    public MessageResponseDto createProfessor(ProfessorRequestDto professorRequestDto) {

        Optional<Professor> existentProfessor = professorRepository.
                getProfessorByCode(professorRequestDto.getPersonID());

        if (existentProfessor.isPresent()){
            throw new BadRequestException("Professor already exists.");
        }

        List<Subject> subjects = new ArrayList<>();
        for (String subjectCode : professorRequestDto.getSubjectsCodeList()){
            Optional<Subject> subjectCandidate = subjectRepository.getSubjectByCode(subjectCode);
            if (subjectCandidate.isEmpty()) {
                throw new BadRequestException("One or more subjects don't exists.");
            }
            subjects.add(subjectCandidate.get());
        }

        Professor professor = Mapper.convertProfessorRequestDtoToProfessor(professorRequestDto, subjects);

        professorRepository.addProfessor(professor);

        return new MessageResponseDto("Professor created successfully.");
    }

}

//
