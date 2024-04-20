package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity;

public class MercadoPago implements IPaymentMethod {
    // TODO: Implement methods
    @Override
    public boolean pay(Double amount) {
        return false;
    }

    @Override
    public boolean verifyPayment() {
        return false;
    }
}
