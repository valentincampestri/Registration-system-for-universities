package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.service.implementation;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.dto.response.MessageResponseDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.dto.SubjectDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.entity.Subject;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.exception.BadRequestException;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.exception.InvalidArgsException;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.exception.NotFoundException;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.repository.ISubjectRepository;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.service.ISubjectService;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.utils.Mapper;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@FieldDefaults(level = AccessLevel.PRIVATE)

@Service
public class SubjectService implements ISubjectService {
    ISubjectRepository subjectRepository;

    public SubjectService(ISubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @Override
    public List<SubjectDto> getAllSubjects() {
        List<Subject> subjectList = subjectRepository.getAllSubject();
        if (subjectList.isEmpty()) {
            throw new NotFoundException("There are no subjects.");
        }
        List<SubjectDto> response = subjectList.stream().map(Mapper::convertSubjectToSubjectDto).toList();
        return response;
    }

    @Override
    public MessageResponseDto createSubject(SubjectDto subjectDto) {
        Subject subject = Mapper.convertSubjectDtoToSubject(subjectDto);
        Optional<Subject> existentSubject = subjectRepository.getSubjectByCode(subjectDto.getSubjectCode());
        if (existentSubject.isPresent()) {
            throw new BadRequestException("Subject already exists.");
        }
        if (subject.getWorkload()<=0){
            throw new InvalidArgsException("Workload must be greater than 0.");
        }
        for (String prerequisite : subject.getPrerequisitesCodeList()) {
            Optional<Subject> existentPrerequisite = subjectRepository.getSubjectByCode(prerequisite);
            if (existentPrerequisite.isEmpty()) {
                throw new NotFoundException("One or more prerequisitesId don´t exist.");
            }
            if (prerequisite.equals(subject.getSubjectCode())) {
                throw new InvalidArgsException("A subject cannot be a prerequisite of itself.");
            }
        }
        subjectRepository.addSubject(subject);
        return new MessageResponseDto("Subject created successfully.");
    }
}
