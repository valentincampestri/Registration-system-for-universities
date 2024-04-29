package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

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