package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.swing.text.Document;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

@AllArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ReportPDF implements IReport {

    // TODO: Implement methods

    @Override
    public void generateReport(Course course) {
        com.itextpdf.text.Document document = new com.itextpdf.text.Document();
        try {
            String professorId = course.getProfessor().getPersonCode();
            PdfWriter.getInstance(document, new FileOutputStream("Report - " + professorId + ".pdf"));
            document.open();

            document.add(new Paragraph("Report of assigned courses for the teacher with ID:" + professorId + "\n\n"));
            document.add(new Paragraph("Course name: " + course.getSubject().getName() + "\n"));
            document.add(new Paragraph("Timetable: " + course.getStartTime() + " - " + course.getEndTime() + "\n"));
            document.add(new Paragraph("Assigned classroom: " + course.getClassroom().getClassroomCode() + "\n\n"));

            document.close();

        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
