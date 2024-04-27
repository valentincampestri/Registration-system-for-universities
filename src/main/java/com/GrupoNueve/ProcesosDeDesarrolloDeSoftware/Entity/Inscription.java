package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Inscription {
    String inscriptionCode;
    Student student;
    Course course;
}
