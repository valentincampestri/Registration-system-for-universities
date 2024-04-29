package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.service;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.Request.CourseRequestDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.Response.FeeResponseDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.Response.MessageResponseDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity.Fee;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity.Inscription;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Exception.BadRequestException;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Exception.InvalidArgsException;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Exception.NotFoundException;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Repository.Implementation.*;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Service.Implementation.CourseService;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Service.Implementation.FeeService;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.utils.Mapper;
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

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@DirtiesContext
@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class FeeServiceTest {
    @Mock
    FeeRepository feeRepository;
    @Mock
    StudentRepository studentRepository;
    @InjectMocks
    FeeService feeService;

    @Test
    @DisplayName("payFee - The student does not have any pending fees.")
    public void payFeeFailNoPendingFee() {
        // Arrange
        String studentCode = "ST99298";
        String paymentMethod = "MercadoPago";
        when(feeRepository.getFeeByStudentCode(studentCode)).thenReturn(List.of());
        // Act & Assert
        BadRequestException exception = assertThrows(BadRequestException.class, () -> feeService.pay(studentCode, paymentMethod));
        Assertions.assertEquals("The student does not have any pending fees.", exception.getMessage());
    }

    @Test
    @DisplayName("payFee - Invalid payment method")
    public void payFeeFailInvalidPaymentMethod() {
        // Arrange
        String studentCode = "ST001";
        String paymentMethod = "Bitcoin";
        when(feeRepository.getFeeByStudentCode(studentCode)).thenReturn(List.of(MockBuilder.mockFee()));
        // Act & Assert
        InvalidArgsException exception = assertThrows(InvalidArgsException.class, () -> feeService.pay(studentCode, paymentMethod));
        Assertions.assertEquals("Invalid payment method: "+paymentMethod, exception.getMessage());
    }

    @Test
    @DisplayName("payFee - MercadoPago - Ok")
    public void payFeeMercadoPagoOk() {
        // Arrange
        String studentCode = "ST001";
        String paymentMethod = "MercadoPago";
        when(feeRepository.getFeeByStudentCode(studentCode)).thenReturn(List.of(MockBuilder.mockFee()));
        // Act
        MessageResponseDto result = feeService.pay(studentCode, paymentMethod);
        // Assert
        Assertions.assertEquals("The fee has been paid successfully.", result.getMessage());
    }

    @Test
    @DisplayName("payFee - Credit_Card - Ok")
    public void payFeeCreditCardOk() {
        // Arrange
        String studentCode = "ST001";
        String paymentMethod = "credit_card";
        when(feeRepository.getFeeByStudentCode(studentCode)).thenReturn(List.of(MockBuilder.mockFee()));
        // Act
        MessageResponseDto result = feeService.pay(studentCode, paymentMethod);
        // Assert
        Assertions.assertEquals("The fee has been paid successfully.", result.getMessage());
    }

    @Test
    @DisplayName("payFee - paypal - Ok")
    public void payFeePayPalOk() {
        // Arrange
        String studentCode = "ST001";
        String paymentMethod = "paypal";
        when(feeRepository.getFeeByStudentCode(studentCode)).thenReturn(List.of(MockBuilder.mockFee()));
        // Act
        MessageResponseDto result = feeService.pay(studentCode, paymentMethod);
        // Assert
        Assertions.assertEquals("The fee has been paid successfully.", result.getMessage());
    }

    @Test
    @DisplayName("getFeesByStudent - The student does not exist.")
    public void getFeesByStudentFailStudentDoesntExist() {
        // Arrange
        String studentCode = "ST99298";
        when(studentRepository.getStudentByCode(studentCode)).thenReturn(Optional.empty());
        // Act & Assert
        NotFoundException exception = assertThrows(NotFoundException.class, () -> feeService.getFeesByStudent(studentCode));
        Assertions.assertEquals("The student does not exist.", exception.getMessage());
    }

    @Test
    @DisplayName("getFeesByStudent - The student does not have fees.")
    public void getFeesByStudentFailNoFeesExist() {
        // Arrange
        String studentCode = "ST001";
        when(studentRepository.getStudentByCode(studentCode)).thenReturn(Optional.of(MockBuilder.mockStudent()));
        when(feeRepository.getFeeByStudentCode(studentCode)).thenReturn(List.of());
        // Act & Assert
        BadRequestException exception = assertThrows(BadRequestException.class, () -> feeService.getFeesByStudent(studentCode));
        Assertions.assertEquals("The student does not have fees.", exception.getMessage());
    }

    @Test
    @DisplayName("getFeesByStudent - Ok")
    public void getFeesByStudentOk() {
        // Arrange
        String studentCode = "ST001";
        Fee fee = MockBuilder.mockFee();
        when(studentRepository.getStudentByCode(studentCode)).thenReturn(Optional.of(MockBuilder.mockStudent()));
        when(feeRepository.getFeeByStudentCode(MockBuilder.mockStudent().getPersonCode())).thenReturn(List.of(fee));
        // Act & Assert
        List<FeeResponseDto> result = feeService.getFeesByStudent(studentCode);
        Assertions.assertEquals(List.of(Mapper.convertFeeToFeeResponseDto(fee)), result);
    }

}