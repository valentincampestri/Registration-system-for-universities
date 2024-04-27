package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Controller;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Service.IPaymentService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    IPaymentService paymentService;

    public PaymentController(IPaymentService paymentService) {
        this.paymentService = paymentService;
    }
}
