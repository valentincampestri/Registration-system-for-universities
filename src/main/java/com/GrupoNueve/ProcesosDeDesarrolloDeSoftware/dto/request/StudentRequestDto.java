package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.dto.request;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.entity.Career;
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
public class StudentRequestDto {
    String name;
    String lastName;
    String personCode;
    String email;
    String phone;
    String address;
    Career career;
    List<String> approvedSubjectsCodeList;
}
