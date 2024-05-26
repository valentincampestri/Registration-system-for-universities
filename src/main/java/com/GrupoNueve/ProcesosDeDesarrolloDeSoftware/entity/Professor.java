package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Map;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Professor extends Person {
    List<Subject> subjects;
    Map<DayOfWeek, Set<Shift>> availability;

    public Professor(String name, String lastName, String personCode, String email, String phone, String address, List<Subject> subjects, Map<DayOfWeek, Set<Shift>> availability) {
        super(name, lastName, personCode, email, phone, address);
        this.subjects = subjects;
        this.availability = availability;
    }

}