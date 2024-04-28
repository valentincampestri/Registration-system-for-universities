package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.Request;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity.Classroom;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity.Term;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class CourseRequestDto {
    String courseCode;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    @Schema(type = "string", format = "HH:mm", example = "09:00")
    LocalTime startTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    @Schema(type = "string", format = "HH:mm", example = "10:00")
    LocalTime endTime;
    String modality;
    Classroom classroom;
    Term term;
    LocalDate lastInscriptionDate;
    List<DayOfWeek> daysList;
    String schedule;
    Double price;
}
