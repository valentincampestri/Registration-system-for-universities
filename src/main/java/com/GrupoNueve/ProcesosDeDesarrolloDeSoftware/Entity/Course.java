package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Course {
    Integer courseID;
    LocalTime startTime;
    LocalTime endTime;
    String modality;
    Professor professor;
    Subject subject;
    Classroom classroom;
    Term term;
    List<DayOfWeek> daysList;
    String schedule;
}