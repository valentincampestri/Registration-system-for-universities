package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;


@AllArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ReportExcel implements IReport {

    @Override
    public void generateReport(List<Course> courses) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Courses Report");

        // Create header row
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Course Name");
        headerRow.createCell(1).setCellValue("Shift");
        headerRow.createCell(2).setCellValue("Assigned Classroom");

        // Fill data
        for (int i = 0; i < courses.size(); i++) {
            Course course = courses.get(i);
            Row row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(course.getSubject().getName());
            row.createCell(1).setCellValue(course.getShift().toString());
            row.createCell(2).setCellValue(course.getClassroom().getClassroomCode());
        }

        // Auto size columns
        for (int i = 0; i < 3; i++) {
            sheet.autoSizeColumn(i);
        }

        // Write to file
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
