package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Repository.Implementation;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity.Payment;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Repository.IPaymentRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class PaymentRepository implements IPaymentRepository {

    private List<Payment> paymentsList = new ArrayList<>();

    @Override
    public void addPayment(Payment payment) {
        paymentsList.add(payment);
    }

    @Override
    public void deletePayment(String paymentId) {
        paymentsList.removeIf(payment -> payment.getFee().getFeeId().equals(paymentId));
    }

    @Override
    public void updatePayment(Payment payment) {
        paymentsList.remove(payment);
        paymentsList.add(payment);
    }

    @Override
    public Optional<Payment> getPaymentById(String paymentId) {
        return paymentsList.stream()
                .filter(payment -> payment.getFee().getFeeId().equals(paymentId))
                .findFirst();
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentsList;
    }
}
