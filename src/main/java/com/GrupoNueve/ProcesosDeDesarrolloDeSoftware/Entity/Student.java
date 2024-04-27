package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class Student extends Person {
    Career career;
    List<Subject> approvedSubjects;

    public Student(String name, String lastName, String personID, String email, String phone, String address, Career career, List<Subject> approvedSubjects) {
        super(name, lastName, personID, email, phone, address);
        this.career = career;
        this.approvedSubjects = approvedSubjects;
    }

}
