package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.service;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.Response.InscriptionResponseDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity.Inscription;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Exception.NotFoundException;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Repository.Implementation.InscriptionRepository;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Service.Implementation.InscriptionService;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.utils.MockBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@DirtiesContext
@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class InscriptionServiceTest {
    @Mock
    InscriptionRepository inscriptionRepository;

    @InjectMocks
    InscriptionService inscriptionService;

    @Test
    @DisplayName("getInscriptionsByStudent - No inscription found for student")
    public void getInscriptionsByStudentNoInscriptionsTest() {
        // Arrange
        String studentCode = "42314";
        when(inscriptionRepository.getAllInscriptions()).thenReturn(new ArrayList<>());

        // Act & Assert
        assertThrows(NotFoundException.class,
                () -> inscriptionService.getInscriptionsByStudent(studentCode));
    }

    @Test
    @DisplayName("getInscriptionsByStudent - Inscription found for student")
    public void getInscriptionsByStudentFoundInscriptionsTest() {
        // Arrange
        String studentCode = "42314";
        List<Inscription> inscriptions = new ArrayList<>();
        inscriptions.add(MockBuilder.mockInscription());
        when(inscriptionRepository.getAllInscriptions()).thenReturn(inscriptions);

        // Act
        List<InscriptionResponseDto> responseDtos = inscriptionService.getInscriptionsByStudent(studentCode);

        // Assert
        Assertions.assertNotNull(responseDtos);
    }

    @Test
    @DisplayName("getAllInscriptions - There are no inscriptions")
    public void getAllInscriptionsFail() {
        // Arrange
        when(inscriptionRepository.getAllInscriptions()).thenReturn(new ArrayList<>());

        // Act & Assert
        NotFoundException exception = assertThrows(NotFoundException.class,
                () -> inscriptionService.getAllInscriptions());
        Assertions.assertEquals("There are no inscriptions.", exception.getMessage());
    }


    @Test
    @DisplayName("getAllInscriptions - Ok")
    public void getAllInscriptionsOk() {
        // Arrange
        String studentCode = "42314";
        List<Inscription> inscriptionList = List.of(MockBuilder.mockInscription());
        when(inscriptionRepository.getAllInscriptions()).thenReturn(inscriptionList);
        List<InscriptionResponseDto> responseDtos = List.of(MockBuilder.mockInscriptoinDto());

        // Act
        List<InscriptionResponseDto> result = inscriptionService.getAllInscriptions();

        // Assert
        Assertions.assertEquals(responseDtos,result);
    }

}
