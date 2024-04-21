package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
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
