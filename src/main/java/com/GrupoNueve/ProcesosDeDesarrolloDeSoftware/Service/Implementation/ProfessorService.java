package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Service.Implementation;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.Request.ProfessorRequestDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.Response.MessageResponseDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity.Professor;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity.Shift;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity.Subject;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Exception.BadRequestException;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Repository.IProfessorRepository;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Repository.ISubjectRepository;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Service.IProfessorService;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.utils.Mapper;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.DayOfWeek;
import java.util.*;

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

        Map<DayOfWeek, Set<Shift>> availability = new HashMap<>();
        for (Map.Entry<String, Set<String>> entry : professorRequestDto.getAvailibility().entrySet()) {
            if (!entry.getKey().equalsIgnoreCase("MONDAY") && !entry.getKey().equalsIgnoreCase("TUESDAY") &&
                    !entry.getKey().equalsIgnoreCase("WEDNESDAY") && !entry.getKey().equalsIgnoreCase("THURSDAY") &&
                    !entry.getKey().equalsIgnoreCase("FRIDAY") && !entry.getKey().equalsIgnoreCase("SATURDAY") &&
                    !entry.getKey().equalsIgnoreCase("SUNDAY")){
                throw new BadRequestException("Invalid day of week.");
            }
            DayOfWeek day = DayOfWeek.valueOf(entry.getKey());
            Set<Shift> shifts = new HashSet<>();
            for (String shift : entry.getValue()) {
                if (!shift.equalsIgnoreCase("MORNING") && !shift.equalsIgnoreCase("AFTERNOON") &&
                        !shift.equalsIgnoreCase("NIGHT")){
                    throw new BadRequestException("Invalid shift.");
                }
                shifts.add(Shift.valueOf(shift.toUpperCase()));
            }
            availability.put(day, shifts);
        }

        Professor professor = Mapper.convertProfessorRequestDtoToProfessor(professorRequestDto, subjects, availability);

        professorRepository.addProfessor(professor);

        return new MessageResponseDto("Professor created successfully.");
    }

    @Override
    public MessageResponseDto calculateMonthlyWorkload(String professorCode) {
        Optional<Professor> existentProfessor = professorRepository.getProfessorByCode(professorCode);
        if (existentProfessor.isEmpty()) {
            throw new BadRequestException("Professor does not exist.");
        }
        Professor professor = existentProfessor.get();
        Integer totalHours = 0;
        for (Subject subject : professor.getSubjects()) {
            totalHours += (subject.getWorkload() / 4); // se divide por 4 porque queremos la carga horaria mensual
        }
        return new MessageResponseDto("Total hours: " + totalHours);
    }

}

//
