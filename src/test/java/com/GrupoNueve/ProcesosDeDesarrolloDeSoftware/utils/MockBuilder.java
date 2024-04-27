package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.utils;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.Request.CourseRequestDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.Response.CourseResponseDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.SubjectDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class MockBuilder {
    public static CourseRequestDto mockCourseRequestDto() {
        return new CourseRequestDto(
                "0",
                LocalTime.parse("09:00"),
                LocalTime.parse("10:00"),
                "virtual",
                new Classroom(
                        50,
                        "701"
                ),
                new Term(
                        LocalDate.parse("2024-04-26"),
                        LocalDate.parse("2024-07-26")
                ),
                List.of(DayOfWeek.MONDAY),
                "Schedule",
                1000D
        );
    }

    public static CourseResponseDto mockCourseResponseDto() {
        return new CourseResponseDto(
                "0",
                LocalTime.parse("09:00"),
                LocalTime.parse("10:00"),
                "virtual",
                new Professor(
                        "string",
                        "string",
                        "string",
                        "string",
                        "string",
                        "string",
                        List.of("1","2")
                ),
                new Subject(
                        "1",
                        "string",
                        0,
                        List.of("10")
                ),
                new Classroom(
                        50,
                        "701"
                ),
                new Term(
                        LocalDate.parse("2024-04-26"),
                        LocalDate.parse("2024-07-26")
                ),
                List.of(DayOfWeek.MONDAY),
                "Schedule",
                1000D
        );
    }

    public static Course mockCourse() {
        return new Course(
                "0",
                LocalTime.parse("09:00"),
                LocalTime.parse("10:00"),
                "virtual",
                new Professor(
                        "string",
                        "string",
                        "string",
                        "string",
                        "string",
                        "string",
                        List.of("1","2")
                ),
                new Subject(
                        "1",
                        "string",
                        0,
                        List.of("10")
                ),
                new Classroom(
                        50,
                        "701"
                ),
                new Term(
                        LocalDate.parse("2024-04-26"),
                        LocalDate.parse("2024-07-26")
                ),
                List.of(DayOfWeek.MONDAY),
                "Schedule",
                1000D
        );
    }

    public static Professor mockProfessor() {
        return new Professor(
                "string",
                "string",
                "string",
                "string",
                "string",
                "string",
                List.of("1","2")
        );
    }

    public static Subject mockSubject() {
        return new Subject(
                "14",
                "string",
                11,
                List.of("11")
        );
    }

    public static SubjectDto mockSubjectDto() {
        return new SubjectDto(
                "14",
                "string",
                11,
                List.of("11")
        );
    }

    public static Subject mockSubjectPrerequisiteItself() {
        return new Subject(
                "13",
                "string",
                10,
                List.of("1","13")
        );
    }

    public static SubjectDto mockSubjectPrerequisiteItselfDto() {
        return new SubjectDto(
                "13",
                "string",
                10,
                List.of("1","13")
        );
    }

    public static SubjectDto mockSubjectInvalidWorkloadDto() {
        return new SubjectDto(
                "15",
                "string",
                0,
                List.of("10")
        );
    }
}
