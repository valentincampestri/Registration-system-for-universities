package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Repository;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity.Payment;

import java.util.List;
import java.util.Optional;

public interface IPaymentRepository {
    void addPayment(Payment payment);

    void deletePayment(String paymentId);

    void updatePayment(Payment payment);

    Optional<Payment> getPaymentById(String paymentId);

    List<Payment> getAllPayments();
}
