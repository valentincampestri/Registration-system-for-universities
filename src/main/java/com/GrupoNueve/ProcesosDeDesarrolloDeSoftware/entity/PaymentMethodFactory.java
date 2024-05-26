package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.entity;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.exception.InvalidArgsException;

public class PaymentMethodFactory {
    public static IPaymentMethod createPaymentMethod(String paymentMethod) {
        String normalizedPaymentMethod = paymentMethod.toLowerCase();
        switch (normalizedPaymentMethod.toLowerCase()) {
            case "binance":
                return new Binance();
            case "mercadopago":
                return new MercadoPago();
            case "pago_mis_cuentas":
                return new PagoMisCuentas();
            default:
                throw new InvalidArgsException("Invalid payment method: " + paymentMethod);
        }
    }
}
