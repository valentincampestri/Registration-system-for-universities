package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.service;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.dto.response.FeeResponseDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.dto.response.MessageResponseDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.entity.Inscription;

import java.util.List;

public interface IFeeService {
    void addFee(Inscription inscription);
    MessageResponseDto pay(String studentCode, String paymentMethod);
    List<FeeResponseDto> getFeesByStudent(String studentCode);
}
