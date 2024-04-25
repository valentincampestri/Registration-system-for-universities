package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class Student extends Person{
    Career career;
    public Student(String name, String lastName, String personID, String email, String phone, String address, Career career) {
        super(name, lastName, personID, email, phone, address);
        this.career = career;
    }

}
