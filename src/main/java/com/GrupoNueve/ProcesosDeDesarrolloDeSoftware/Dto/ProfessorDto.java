package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity.Subject;
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
public class ProfessorDto {
    String name;
    String lastName;
    String personID;
    String email;
    String phone;
    String address;
    List<Subject> subjects;

}
