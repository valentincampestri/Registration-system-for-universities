package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.entity;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.repository.IInscriptionRepository;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

@AllArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ReportPDF implements IReport {
    private IInscriptionRepository inscriptionRepository;

    @Override
    public void generateReport(List<Course> courses) {
        com.itextpdf.text.Document document = new com.itextpdf.text.Document();
        try {
            String professorId = courses.get(0).getProfessor().getPersonCode();
            PdfWriter.getInstance(document, new FileOutputStream("Report - " + professorId + ".pdf"));
            document.open();

            document.add(new Paragraph("Report of assigned courses for the teacher with ID:" + professorId + "\n\n"));

            for (Course course : courses) {
                document.add(new Paragraph("CourseCode: " + course.getCourseCode() + "\n"));
                document.add(new Paragraph("Subject: " + course.getSubject().getName() + "\n"));
                document.add(new Paragraph("Shift: " + course.getShift() + "\n"));
                document.add(new Paragraph("Assigned classroom: " + course.getClassroom().getClassroomCode() + "\n"));
                document.add(new Paragraph("Term code: " + course.getTerm().getTermCode() + "\n"));
                document.add(new Paragraph("Student count: " + inscriptionRepository.getInscriptionsByCourseCode(course.getCourseCode()).size()));
                document.add(new Paragraph("\n---\n\n"));
            }

            document.close();

        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
