package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class Student extends Person {
    Career career;
    List<Subject> approvedSubjectList;

    public Student(String name, String lastName, String personCode, String email, String phone, String address, Career career, List<Subject> approvedSubjectList) {
        super(name, lastName, personCode, email, phone, address);
        this.career = career;
        this.approvedSubjectList = approvedSubjectList;
    }
}
