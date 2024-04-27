package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Repository;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity.Fee;

import java.util.List;
import java.util.Optional;

public interface IFeeRepository {
    void addFee(Fee payment);

    void deleteFee(String paymentId);

    void updateFee(Fee payment);

    Optional<Fee> getFeeByCode(String paymentId);

    List<Fee> getAllFees();
}
