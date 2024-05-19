package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import java.util.List;

@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Fee {
    static String currentCode="1";
    String feeCode;
    List<Inscription> inscriptions;
    Double price;
    Boolean isPaid;

    public Fee(List<Inscription> inscriptions, Double price, Boolean isPaid) {
        this.feeCode = currentCode;
        this.inscriptions = inscriptions;
        this.price = price;
        this.isPaid = isPaid;
        currentCode = String.valueOf(Integer.parseInt(currentCode) + 1);
    }
}
