package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Course {
    String courseCode;
    Shift shift;
    String modality;
    Professor professor;
    Subject subject;
    Classroom classroom;
    Term term;
    LocalDate lastInscriptionDate;
    List<DayOfWeek> daysList;
    String schedule;
    Double price;
}