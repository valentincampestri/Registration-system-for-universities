package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class MercadoPago implements IPaymentMethod {
    String cvu;
    String alias;
    String cuil;


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
