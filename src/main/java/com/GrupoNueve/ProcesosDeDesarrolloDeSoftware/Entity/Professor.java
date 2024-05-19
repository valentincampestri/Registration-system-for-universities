package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Professor extends Person {
    List<Subject> subjects;

    public Professor(String name, String lastName, String personCode, String email, String phone, String address, List<Subject> subjects) {
        super(name, lastName, personCode, email, phone, address);
        this.subjects = subjects;
    }

}