package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity;

public interface IPaymentMethod {
    public boolean pay(Double amount);
    public boolean verifyPayment();
}
