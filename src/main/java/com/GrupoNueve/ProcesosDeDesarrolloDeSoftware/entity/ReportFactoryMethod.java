package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.entity;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.exception.BadRequestException;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.repository.IInscriptionRepository;

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
