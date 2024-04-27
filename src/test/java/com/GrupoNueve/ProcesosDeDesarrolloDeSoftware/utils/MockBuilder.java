package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.utils;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.Request.CourseRequestDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.Response.CourseResponseDto;
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
                "Schedule"
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
                        "string"
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
                "Schedule"
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
                        "string"
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
                "Schedule"
        );
    }

    public static Professor mockProfessor() {
        return new Professor(
                "string",
                "string",
                "string",
                "string",
                "string",
                "string"
        );
    }

    public static Subject mockSubject() {
        return new Subject(
                "1",
                "string",
                0,
                List.of("10")
        );
    }
}
