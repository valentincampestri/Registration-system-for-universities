package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Service.Implementation;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Repository.IPaymentRepository;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Service.IPaymentService;
import org.springframework.stereotype.Service;

@Service
public class PaymentService implements IPaymentService {
    IPaymentRepository paymentRepository;

    public PaymentService(IPaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }
}
