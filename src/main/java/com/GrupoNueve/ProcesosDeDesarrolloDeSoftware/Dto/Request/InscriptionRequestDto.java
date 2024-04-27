package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.Request;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity.Student;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InscriptionRequestDto {
    String id;
}
