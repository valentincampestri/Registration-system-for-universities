package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Fee {
    static String currentCode="0";
    String feeCode;
    List<Course> courses;
    Student student;
    Double price;
    Boolean isPaid;

    public Fee(List<Course> courses, Student student, Double price, Boolean isPaid) {
        this.feeCode = currentCode;
        this.courses = courses;
        this.student = student;
        this.price = price;
        this.isPaid = isPaid;
        currentCode = String.valueOf(Integer.parseInt(currentCode) + 1);
    }
}
