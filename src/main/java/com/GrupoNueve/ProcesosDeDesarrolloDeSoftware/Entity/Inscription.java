package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Inscription {
    Student student;
    Course course;

    // TODO: Implement this method
    public boolean payFee(Fee fee) {
        return true;
    }
}
