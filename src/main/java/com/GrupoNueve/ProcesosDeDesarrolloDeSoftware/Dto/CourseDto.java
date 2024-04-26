package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity.Classroom;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity.Professor;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity.Subject;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity.Term;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDto {
    private Integer courseID;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    @Schema(type = "string", format = "HH:mm", example = "09:00")
    private LocalTime startTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    @Schema(type = "string", format = "HH:mm", example = "10:00")
    private LocalTime endTime;
    private String modality;
    private Professor professor;
    private Subject subject;
    private Classroom classroom;
    private Term term;
    private List<DayOfWeek> daysList;
    private String schedule;
}
