package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto;

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
public class SubjectDto {
    String subjectCode;
    String name;
    Integer workload;
    List<String> prerequisitesCodeList;
}
