package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
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
