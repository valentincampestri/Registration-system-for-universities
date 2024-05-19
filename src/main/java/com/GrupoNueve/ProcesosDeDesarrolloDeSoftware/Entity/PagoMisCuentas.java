package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class PagoMisCuentas implements IPaymentMethod {

    // TODO: Implement methods
    @Override
    public boolean pay(Double amount) {
        return true;
    }
}
