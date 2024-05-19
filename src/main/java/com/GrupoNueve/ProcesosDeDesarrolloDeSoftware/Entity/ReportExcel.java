package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@AllArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ReportExcel implements IReport{
    @Override
    public void generateReport(List<Course> courses) {
        System.out.println("Generating Excel report...");
    }
}
