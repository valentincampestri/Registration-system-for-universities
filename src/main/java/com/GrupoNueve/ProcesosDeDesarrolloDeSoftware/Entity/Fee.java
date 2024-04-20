package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Fee {
    Integer feeNumber;
    List<Course> courses;
    Student student;

    public void addCourse(Course course) {
        courses.add(course);
    }
}
