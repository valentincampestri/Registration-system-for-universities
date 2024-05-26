package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class PagoMisCuentas implements IPaymentMethod {

    @Override
    public boolean pay(Double amount) {
        return true;
    }
}