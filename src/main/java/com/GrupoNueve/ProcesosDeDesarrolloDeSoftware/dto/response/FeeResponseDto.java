package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.dto.response;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.entity.Inscription;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FeeResponseDto {
    String feeCode;
    List<Inscription> inscriptions;
    Double price;
    Boolean isPaid;
}
