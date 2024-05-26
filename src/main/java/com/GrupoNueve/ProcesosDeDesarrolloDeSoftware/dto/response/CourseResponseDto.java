package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.dto.response;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.entity.Classroom;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.entity.Professor;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.entity.Shift;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.entity.Subject;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.entity.Term;
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