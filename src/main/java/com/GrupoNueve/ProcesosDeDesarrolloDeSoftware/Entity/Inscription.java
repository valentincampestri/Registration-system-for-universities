package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Inscription {
    static String currentCode="0";
    String inscriptionCode;
    Student student;
    List<Course> courses;

    public Inscription(Student student, List<Course> courses) {
        this.inscriptionCode = currentCode;
        this.student = student;
        this.courses = courses;
        currentCode = String.valueOf(Integer.parseInt(currentCode) + 1);
    }
}