package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Subject {
    String subjectID;
    String name;
    String workload;
}
