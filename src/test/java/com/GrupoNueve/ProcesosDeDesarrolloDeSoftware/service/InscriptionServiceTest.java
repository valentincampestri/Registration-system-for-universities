package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.service;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.Request.CourseRequestDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Exception.NotFoundException;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Repository.Implementation.*;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Service.Implementation.CourseService;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Service.Implementation.FeeService;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Service.Implementation.InscriptionService;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.utils.MockBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Optional;

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
    @DisplayName("create - Error")
    public void createFail() {
        // Arrange
        // Act & Assert
    }

    @Test
    @DisplayName("create - Ok")
    public void createOk() {
        // Arrange
        // Act
        // Assert
    }
}