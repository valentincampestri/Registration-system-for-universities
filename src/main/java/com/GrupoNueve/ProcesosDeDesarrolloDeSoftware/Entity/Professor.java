package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class Professor extends Person{
    final List<Subject> subjects;

    public Professor(String name, String lastName, String personID, String email, String phone, String address, List<Subject> subjects) {
        super(name, lastName, personID, email, phone, address);
        this.subjects = subjects;
    }

    public void addSubject(Subject subject){
        this.subjects.add(subject);
    }

}
