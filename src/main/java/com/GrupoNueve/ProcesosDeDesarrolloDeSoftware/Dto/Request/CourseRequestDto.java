package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.Request;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity.Classroom;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity.Shift;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity.Term;
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

public class CourseRequestDto {
    String courseCode;
    Shift shift;
    String modality;
    Classroom classroom;
    Term term;
    LocalDate lastInscriptionDate;
    List<DayOfWeek> daysList;
    String schedule;
    Double price;
}
