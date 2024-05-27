package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.entity;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.repository.IInscriptionRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;


@AllArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ReportExcel implements IReport {
    private IInscriptionRepository inscriptionRepository;

    @Override
    public void generateReport(List<Course> courses) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Courses Report");

        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Professor Code");
        headerRow.createCell(1).setCellValue("Course Code");
        headerRow.createCell(2).setCellValue("Subject");
        headerRow.createCell(3).setCellValue("Shift");
        headerRow.createCell(4).setCellValue("Assigned Classroom");
        headerRow.createCell(5).setCellValue("Term code");
        headerRow.createCell(6).setCellValue("Student count");

        for (int i = 0; i < courses.size(); i++) {
            Course course = courses.get(i);
            Row row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(course.getProfessor().getPersonCode());
            row.createCell(1).setCellValue(course.getCourseCode());
            row.createCell(2).setCellValue(course.getSubject().getName());
            row.createCell(3).setCellValue(course.getShift().toString());
            row.createCell(4).setCellValue(course.getClassroom().getClassroomCode());
            row.createCell(5).setCellValue(course.getTerm().getTermCode());
            row.createCell(6).setCellValue(inscriptionRepository.getInscriptionsByCourseCode(course.getCourseCode()).size());
        }

        for (int i = 0; i < 5; i++) {
            sheet.autoSizeColumn(i);
        }

        try {
            String professorId = courses.get(0).getProfessor().getPersonCode();
            FileOutputStream fileOut = new FileOutputStream("Report - " + professorId + ".xlsx");
            workbook.write(fileOut);
            fileOut.close();
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
