package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Payment {
    IPaymentMethod paymentMethod;
    Fee fee;

    // TODO: Implement this method
    public boolean payFee() {
        return false;
    }

}
