package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Service.Implementation;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.Response.InscriptionResponseDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity.Inscription;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Exception.NotFoundException;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Repository.IInscriptionRepository;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Service.IInscriptionService;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.utills.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InscriptionService implements IInscriptionService {
    IInscriptionRepository inscriptionRepository;

    public InscriptionService(IInscriptionRepository inscriptionRepository) {
        this.inscriptionRepository = inscriptionRepository;
    }

    @Override
    public List<InscriptionResponseDto> getInscriptionsByStudent(String studentCode) {
        List<Inscription> allInscriptions = this.inscriptionRepository.getAllInscriptions();

        if (allInscriptions.isEmpty()) {
            throw new NotFoundException("There are no inscriptions.");
        }
        List<Inscription> inscriptionsFilteredByStudent = allInscriptions.stream()
                .filter(inscription -> inscription.getStudent().getPersonCode().equals(studentCode))
                .collect(Collectors.toList());

        List<InscriptionResponseDto> responseDtos = inscriptionsFilteredByStudent.stream()
                .map(Mapper::convertInscriptionToInscriptionResponseDto)
                .collect(Collectors.toList());

        return responseDtos;
    }

    @Override
    public List<InscriptionResponseDto> getAllInscriptions(){
        List<Inscription> inscriptionList = inscriptionRepository.getAllInscriptions();
        if (inscriptionList.isEmpty()) {
            throw new NotFoundException("There are no inscriptions.");
        }
        List<InscriptionResponseDto> responseDtoList = inscriptionList.stream().map
                (Mapper::convertInscriptionToInscriptionResponseDto).toList();
        return responseDtoList;
    }

}