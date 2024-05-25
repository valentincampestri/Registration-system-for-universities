package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Exception.BadRequestException;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Repository.IInscriptionRepository;

import java.util.List;

public class ReportFactoryMethod {

    public static IReport createReport(String reportFormat, IInscriptionRepository inscriptionRepository) {
        String normalizedReportType = reportFormat.toLowerCase();
        return switch (normalizedReportType) {
            case "pdf" -> new ReportPDF(inscriptionRepository);
            case "excel" -> new ReportExcel(inscriptionRepository);
            default -> throw new BadRequestException("Invalid report format.");
        };
    }
}
