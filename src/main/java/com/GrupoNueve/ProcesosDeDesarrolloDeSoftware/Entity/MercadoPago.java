package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Random;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class MercadoPago implements IPaymentMethod {
    /*String cvu;
    String alias;
    String cuil;*/

    // TODO: Implement methods
    @Override
    public boolean pay(Double amount) {
        return true;
    }
}
