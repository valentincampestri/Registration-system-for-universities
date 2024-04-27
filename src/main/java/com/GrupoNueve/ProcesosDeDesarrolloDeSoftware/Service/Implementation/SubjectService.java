package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Service.Implementation;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.Response.CourseResponseDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.Response.MessageResponseDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.SubjectDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity.Professor;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity.Subject;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Exception.BadRequestException;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Exception.InvalidArgsException;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Exception.NotFoundException;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Repository.ISubjectRepository;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Service.ISubjectService;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.utills.Mapper;
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
        Optional<Subject> existentSubject = subjectRepository.getSubjectById(subjectDto.getSubjectID());
        if (existentSubject.isPresent()) {
            throw new BadRequestException("Subject already exists.");
        }
        if (subject.getWorkload()<=0){
            throw new InvalidArgsException("Workload must be greater than 0.");
        }
        //para los prerequisitos hacer una lista, mappear esa lsita, y que verifique en repository  no existe
        for (String prerequisite : subject.getPrerequisites()) {
            Optional<Subject> existentPrerequisite = subjectRepository.getSubjectById(prerequisite);
            if (existentPrerequisite.isEmpty()) {
                throw new NotFoundException("One or more Prerequisites don´t exist.");
            }
            if (prerequisite.equals(subject.getSubjectID())) {
                throw new InvalidArgsException("A subject cannot be a prerequisite of itself.");
            }
        }
        subjectRepository.addSubject(subject);
        return new MessageResponseDto("Subject created successfully.");
    }
}
