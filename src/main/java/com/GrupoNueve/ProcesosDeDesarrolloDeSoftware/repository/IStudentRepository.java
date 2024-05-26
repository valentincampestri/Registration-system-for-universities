package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.repository;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.entity.Student;

import java.util.List;
import java.util.Optional;

public interface IStudentRepository {
    void addStudent(Student student);

    void deleteStudent(String studentCode);

    void updateStudent(Student student);

    Optional<Student> getStudentByCode(String studentCode);

    List<Student> getAllStudents();
}
