package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.utils;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.Request.CourseRequestDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.Request.InscriptionRequestDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.Request.ProfessorRequestDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.Response.CourseResponseDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.Response.FeeResponseDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.Request.StudentRequestDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.SubjectDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity.Career;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity.Classroom;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity.Course;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity.Fee;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity.Inscription;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity.Professor;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity.Shift;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity.Student;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity.Subject;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity.Term;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

public class MockBuilder {
    public static CourseRequestDto mockCourseRequestDto() {
        return new CourseRequestDto(
                "0",
                Shift.MORNING,
                "virtual",
                new Classroom(
                        50,
                        "701"
                ),
                new Term(
                        "1T-2024",
                        LocalDate.parse("2025-04-26"),
                        LocalDate.parse("2025-07-26")
                ),
                LocalDate.parse("2025-05-26"),
                List.of(DayOfWeek.MONDAY),
                "Schedule",
                1000D
        );
    }

    public static CourseResponseDto mockCourseResponseDto() {
        return new CourseResponseDto(
                "0",
                Shift.MORNING,
                "virtual",
                new Professor(
                        "string",
                        "string",
                        "string",
                        "string",
                        "string",
                        "string",
                        List.of(
                                new Subject(
                                        "1",
                                        "Physics",
                                        40,
                                        List.of("0")
                                )
                        )
                ),
                new Subject(
                        "1",
                        "Physics",
                        40,
                        List.of("0")
                ),
                new Classroom(
                        50,
                        "701"
                ),
                new Term(
                        "1T-2024",
                        LocalDate.parse("2025-04-26"),
                        LocalDate.parse("2025-07-26")
                ),
                LocalDate.parse("2025-05-26"),
                List.of(DayOfWeek.MONDAY),
                "Schedule",
                1000D
        );
    }

    public static Course mockCourse() {
        return new Course(
                "0",
                Shift.MORNING,
                "virtual",
                new Professor(
                        "string",
                        "string",
                        "string",
                        "string",
                        "string",
                        "string",
                        List.of(
                                new Subject(
                                        "1",
                                        "Physics",
                                        40,
                                        List.of("0")
                                )
                        )
                ),
                new Subject(
                        "1",
                        "Physics",
                        40,
                        List.of("0")
                ),
                new Classroom(
                        50,
                        "701"
                ),
                new Term(
                        "1T-2024",
                        LocalDate.parse("2025-04-26"),
                        LocalDate.parse("2025-07-26")
                ),
                LocalDate.parse("2025-05-26"),
                List.of(DayOfWeek.MONDAY),
                "Schedule",
                1000D
        );
    }

    public static Course mockCourseMaxCapacity() {
        List<Subject> subjects = List.of(new Subject("S002", "Computer Science", 60, List.of("S002")));
        return new Course("CS500",
                Shift.AFTERNOON,
                "Presencial",
                new Professor(
                        "John",
                        "Doe",
                        "P001",
                        "john.doe@example.com",
                        "123-456-7890",
                        "123 Main St",
                        subjects
                ),
                new Subject(
                        "S002",
                        "Computer Science",
                        60,
                        List.of("S002")
                ),
                new Classroom(
                        0,
                        "C101"
                ),
                new Term(
                        "2T-2024",
                        LocalDate.parse("2024-09-01"),
                        LocalDate.parse("2024-12-20")
                ),
                LocalDate.parse("2024-10-01"),
                List.of(DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY, DayOfWeek.FRIDAY),
                "Exam 1: Class 5, Exam 2: Class 10, Make-up/Early final: Class 11, Regular final: Class 13",
                1000D
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

    public static Subject mockSubjectTwo() {
        return new Subject(
                "1",
                "Physics",
                40,
                List.of("0")
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
                List.of("1", "13")
        );
    }

    public static SubjectDto mockSubjectPrerequisiteItselfDto() {
        return new SubjectDto(
                "13",
                "string",
                10,
                List.of("1", "13")
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

    public static Inscription mockInscription() {
        List<Subject> subjects = List.of(new Subject("S002", "Computer Science", 60, List.of("S002")));
        return new Inscription(
                mockStudent(),
                List.of(
                        new Course(
                                "CS101",
                                Shift.MORNING,
                                "Presencial",
                                new Professor(
                                        "John",
                                        "Doe",
                                        "P001",
                                        "john.doe@example.com",
                                        "123-456-7890",
                                        "123 Main St",
                                        subjects
                                ),
                                new Subject(
                                        "S002",
                                        "Computer Science",
                                        60,
                                        List.of("S002")
                                ),
                                new Classroom(
                                        30,
                                        "C101"
                                ),
                                new Term(
                                        "2T-2024",
                                        LocalDate.parse("2024-09-01"),
                                        LocalDate.parse("2024-12-20")
                                ),
                                LocalDate.parse("2024-10-01"),
                                List.of(DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY, DayOfWeek.FRIDAY),
                                "Exam 1: Class 5, Exam 2: Class 10, Make-up/Early final: Class 11, Regular final: Class 13",
                                1000D
                        )
                )
        );
    }

    public static Inscription mockInscriptionMaxCapacity() {
        List<Subject> subjects = List.of(new Subject("S002", "Computer Science", 60, List.of("S002")));
        return new Inscription(
                mockStudent(),
                List.of(
                        new Course(
                                "CS500",
                                Shift.AFTERNOON,
                                "Presencial",
                                new Professor(
                                        "John",
                                        "Doe",
                                        "P001",
                                        "john.doe@example.com",
                                        "123-456-7890",
                                        "123 Main St",
                                        subjects
                                ),
                                new Subject(
                                        "S002",
                                        "Computer Science",
                                        60,
                                        List.of("S002")
                                ),
                                new Classroom(
                                        0,
                                        "C101"
                                ),
                                new Term(
                                        "2T-2024",
                                        LocalDate.parse("2024-09-01"),
                                        LocalDate.parse("2024-12-20")
                                ),
                                LocalDate.parse("2024-10-01"),
                                List.of(DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY, DayOfWeek.FRIDAY),
                                "Exam 1: Class 5, Exam 2: Class 10, Make-up/Early final: Class 11, Regular final: Class 13",
                                1000D
                        )
                )
        );
    }

    public static InscriptionRequestDto mockInscriptionRequestDto() {
        Inscription inscription = mockInscription();
        return new InscriptionRequestDto(
                inscription.getCourses().stream().map(Course::getCourseCode).toList());
    }

    public static InscriptionRequestDto mockInscriptionRequestMaxCapacityDto() {
        Inscription inscription = mockInscriptionMaxCapacity();
        return new InscriptionRequestDto(
                inscription.getCourses().stream().map(Course::getCourseCode).toList());
    }

    public static Fee mockFee() {
        return new Fee(
                List.of(mockInscription()),
                1000D,
                false
        );
    }

    public static Student mockStudent() {
        List<Subject> subjects = List.of(new Subject("S002", "Computer Science", 60, List.of("S002")));
        return new Student(
                "Alice",
                "Smith",
                "ST001",
                "alice.smith@example.com",
                "987-654-3210",
                "456 Oak St",
                new Career(
                        "C001",
                        "Computer Science",
                        240
                ),
                subjects
        );
    }

    public static StudentRequestDto mockStudentRequestDto() {
        return Mapper.convertStudentToStudentRequestDto(mockStudent());
    }

    public static FeeResponseDto mockFeeResponseDto() {
        return Mapper.convertFeeToFeeResponseDto(mockFee());
    }

    public static Professor mockProfessor() {
        return new Professor(
                "string",
                "string",
                "string",
                "string",
                "string",
                "string",
                List.of(
                        new Subject(
                                "1",
                                "Physics",
                                40,
                                List.of("0")
                        )
                )
        );
    }

    public static ProfessorRequestDto mockProfessorRequestDto() {
        return Mapper.convertProfessorToProfessorRequestDto(mockProfessor());
    }
}

