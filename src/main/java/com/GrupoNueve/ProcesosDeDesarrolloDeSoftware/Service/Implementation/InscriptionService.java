package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Service.Implementation;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.Request.InscriptionRequestDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.Response.InscriptionResponseDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.Response.MessageResponseDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Repository.IInscriptionRepository;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Service.IInscriptionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InscriptionService implements IInscriptionService {
    IInscriptionRepository inscriptionRepository;

    public InscriptionService(IInscriptionRepository inscriptionRepository) {
        this.inscriptionRepository = inscriptionRepository;
    }

    @Override
    public MessageResponseDto createInscription(InscriptionRequestDto inscriptionRequestDto, String studentCode) {


        return new MessageResponseDto("You have successfully enrolled in the selected courses. Now you can pay according to your student code.");
    }
}
