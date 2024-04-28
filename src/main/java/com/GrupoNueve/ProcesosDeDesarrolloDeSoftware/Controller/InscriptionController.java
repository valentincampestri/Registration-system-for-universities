package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Controller;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.Request.InscriptionRequestDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Service.IInscriptionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inscription")
public class InscriptionController {
    IInscriptionService inscriptionService;

    public InscriptionController(IInscriptionService inscriptionService) {
        this.inscriptionService = inscriptionService;
    }

    @PostMapping("/create/{studentCode}/")
    public ResponseEntity<?> createInscription(InscriptionRequestDto inscriptionRequestDto, @PathVariable String studentCode) {
        return ResponseEntity.ok(inscriptionService.createInscription(inscriptionRequestDto, studentCode));
    }
}
