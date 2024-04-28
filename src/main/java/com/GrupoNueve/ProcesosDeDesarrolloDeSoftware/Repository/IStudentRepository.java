package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Repository;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity.Student;

import java.util.List;
import java.util.Optional;

public interface IStudentRepository {
    void addStudent(Student student);

    void deleteStudent(String studentCode);

    void updateStudent(Student student);

    Optional<Student> getStudentByCode(String studentCode);

    List<Student> getAllStudents();
}
