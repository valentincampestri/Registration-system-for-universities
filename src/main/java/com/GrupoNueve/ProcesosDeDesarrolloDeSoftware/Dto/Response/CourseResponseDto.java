package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.Response;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CourseResponseDto {
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