package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class Professor extends Person{
    List<Subject> subjects;

    public Professor(String name, String lastName, String personID, String email, String phone, String address) {
        super(name, lastName, personID, email, phone, address);
        this.subjects = subjects;
    }
}