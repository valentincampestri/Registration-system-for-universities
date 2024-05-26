package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.repository;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.entity.Fee;

import java.util.List;

public interface IFeeRepository {
    void addFee(Fee payment);

    void deleteFee(String paymentId);

    void updateFee(Fee payment);

    List<Fee> getFeeByStudentCode(String studentCode);

    List<Fee> getAllFees();
}
