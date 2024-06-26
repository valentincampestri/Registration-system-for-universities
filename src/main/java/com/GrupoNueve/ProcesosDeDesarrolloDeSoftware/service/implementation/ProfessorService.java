package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.service.implementation;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.dto.request.ProfessorRequestDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.dto.response.MessageResponseDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.entity.Professor;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.entity.Shift;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.entity.Subject;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.exception.BadRequestException;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.repository.IProfessorRepository;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.repository.ISubjectRepository;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.service.IProfessorService;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.utils.Mapper;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

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

        if (existentProfessor.isPresent()) {
            throw new BadRequestException("Professor already exists.");
        }

        List<Subject> subjects = new ArrayList<>();
        for (String subjectCode : professorRequestDto.getSubjectsCodeList()) {
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
                    !entry.getKey().equalsIgnoreCase("SUNDAY")) {
                throw new BadRequestException("Invalid day of week.");
            }
            DayOfWeek day = DayOfWeek.valueOf(entry.getKey());
            Set<Shift> shifts = new HashSet<>();
            for (String shift : entry.getValue()) {
                if (!shift.equalsIgnoreCase("MORNING") && !shift.equalsIgnoreCase("AFTERNOON") &&
                        !shift.equalsIgnoreCase("NIGHT")) {
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
            totalHours += (subject.getWorkload() / 4);
        }
        return new MessageResponseDto("Total hours: " + totalHours);
    }

}