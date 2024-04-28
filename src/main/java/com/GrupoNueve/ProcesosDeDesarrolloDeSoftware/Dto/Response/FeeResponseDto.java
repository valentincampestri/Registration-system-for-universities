package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.Response;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity.Course;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity.Student;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FeeResponseDto {
    String feeCode;
    List<Course> courses;
    Student student;
    Double price;
    Boolean isPaid;
}
