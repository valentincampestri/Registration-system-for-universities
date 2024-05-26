package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.dto.request;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.entity.Classroom;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.entity.Shift;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.entity.Term;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

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
    List<String> daysList;
    String schedule;
    Double price;
}
