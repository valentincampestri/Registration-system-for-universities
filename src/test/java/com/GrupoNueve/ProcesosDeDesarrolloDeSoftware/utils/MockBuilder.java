package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.utils;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.dto.request.CourseRequestDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.dto.request.InscriptionRequestDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.dto.request.ProfessorRequestDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.dto.request.StudentRequestDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.dto.response.CourseResponseDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.dto.SubjectDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.entity.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
                List.of("MONDAY"),
                "Schedule",
                1000D
        );
    }

    public static CourseRequestDto mockCourseRequest2Dto() {
        return new CourseRequestDto(
                "1",
                Shift.AFTERNOON,
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
                List.of("TUESDAY"),
                "Schedule",
                1000D
        );
    }

    public static CourseRequestDto mockCourseRequestFailMaxCapacityDto() {
        return new CourseRequestDto(
                "0",
                Shift.MORNING,
                "virtual",
                new Classroom(
                        0,
                        "701"
                ),
                new Term(
                        "1T-2024",
                        LocalDate.parse("2025-04-26"),
                        LocalDate.parse("2025-07-26")
                ),
                LocalDate.parse("2025-05-26"),
                List.of("MONDAY"),
                "Schedule",
                1000D
        );
    }

    public static CourseRequestDto mockCourseRequestFailDayOfWeekDto() {
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
                List.of("Kimba"),
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
                        ),
                        Map.of(
                                DayOfWeek.MONDAY, Set.of(Shift.MORNING, Shift.AFTERNOON),
                                DayOfWeek.TUESDAY, Set.of(Shift.AFTERNOON),
                                DayOfWeek.WEDNESDAY, Set.of(Shift.NIGHT)
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
                        ),
                        Map.of(
                                DayOfWeek.MONDAY, Set.of(Shift.MORNING, Shift.AFTERNOON),
                                DayOfWeek.TUESDAY, Set.of(Shift.AFTERNOON),
                                DayOfWeek.WEDNESDAY, Set.of(Shift.NIGHT)
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

    public static Course mockCourseTwo() {
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
                                        List.of()
                                )
                        ),
                        Map.of(
                                DayOfWeek.MONDAY, Set.of(Shift.MORNING, Shift.AFTERNOON),
                                DayOfWeek.TUESDAY, Set.of(Shift.AFTERNOON),
                                DayOfWeek.WEDNESDAY, Set.of(Shift.NIGHT)
                        )
                ),
                new Subject(
                        "1",
                        "Physics",
                        40,
                        List.of()
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
        List<Subject> subjects = List.of(new Subject("S002", "Computer Science", 60, List.of()));
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
                        subjects,
                        Map.of(
                                DayOfWeek.MONDAY, Set.of(Shift.MORNING, Shift.AFTERNOON),
                                DayOfWeek.TUESDAY, Set.of(Shift.AFTERNOON),
                                DayOfWeek.WEDNESDAY, Set.of(Shift.NIGHT)
                        )
                ),
                new Subject(
                        "S002",
                        "Computer Science",
                        60,
                        List.of()
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
                                        subjects,
                                        Map.of(
                                                DayOfWeek.MONDAY, Set.of(Shift.MORNING, Shift.AFTERNOON),
                                                DayOfWeek.TUESDAY, Set.of(Shift.AFTERNOON),
                                                DayOfWeek.WEDNESDAY, Set.of(Shift.NIGHT)
                                        )
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
        List<Subject> subjects = List.of(new Subject("S002", "Computer Science", 60, List.of()));
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
                                        subjects,
                                        Map.of(
                                                DayOfWeek.MONDAY, Set.of(Shift.MORNING, Shift.AFTERNOON),
                                                DayOfWeek.TUESDAY, Set.of(Shift.AFTERNOON),
                                                DayOfWeek.WEDNESDAY, Set.of(Shift.NIGHT))
                                ),
                                new Subject(
                                        "S002",
                                        "Computer Science",
                                        60,
                                        List.of()
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
        List<Subject> subjects = List.of(new Subject("S001", "Computer Science", 50, List.of()));
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
                ),
                Map.of(
                        DayOfWeek.MONDAY, Set.of(Shift.MORNING, Shift.AFTERNOON),
                        DayOfWeek.TUESDAY, Set.of(Shift.AFTERNOON),
                        DayOfWeek.WEDNESDAY, Set.of(Shift.NIGHT))
        );
    }

    public static Professor mockProfessorTwo() {

        return new Professor(
                "string2",
                "string2",
                "string2",
                "string2",
                "string2",
                "string2",
                List.of(
                        new Subject(
                                "14",
                                "string",
                                11,
                                List.of("11")
                        )
                ),
                Map.of(
                        DayOfWeek.MONDAY, Set.of(Shift.MORNING, Shift.AFTERNOON),
                        DayOfWeek.TUESDAY, Set.of(Shift.AFTERNOON),
                        DayOfWeek.WEDNESDAY, Set.of(Shift.NIGHT))
        );
    }

    public static ProfessorRequestDto mockProfessorRequestDtoWithInvalidDay() {
        ProfessorRequestDto professorRequestDto = mockProfessorRequestDto();
        Map<String, Set<String>> availabilityWithInvalidDay = new HashMap<>(professorRequestDto.getAvailibility());
        availabilityWithInvalidDay.put("INVALID_DAY", Set.of("MORNING"));
        return new ProfessorRequestDto(
                professorRequestDto.getName(),
                professorRequestDto.getLastName(),
                professorRequestDto.getPersonID(),
                professorRequestDto.getEmail(),
                professorRequestDto.getPhone(),
                professorRequestDto.getAddress(),
                professorRequestDto.getSubjectsCodeList(),
                availabilityWithInvalidDay
        );
    }

    public static ProfessorRequestDto mockProfessorRequestDtoWithInvalidShift() {
        ProfessorRequestDto professorRequestDto = mockProfessorRequestDto();
        Map<String, Set<String>> availabilityWithInvalShift = new HashMap<>(professorRequestDto.getAvailibility());
        availabilityWithInvalShift.put("MONDAY", Set.of("INVALID_SHIFT"));
        return new ProfessorRequestDto(
                professorRequestDto.getName(),
                professorRequestDto.getLastName(),
                professorRequestDto.getPersonID(),
                professorRequestDto.getEmail(),
                professorRequestDto.getPhone(),
                professorRequestDto.getAddress(),
                professorRequestDto.getSubjectsCodeList(),
                availabilityWithInvalShift
        );
    }

    public static ProfessorRequestDto mockProfessorRequestDto() {
        return Mapper.convertProfessorToProfessorRequestDto(mockProfessor());
    }
}

