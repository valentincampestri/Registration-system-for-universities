package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Repository.Implementation;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity.Student;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity.Subject;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Repository.IStudentRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class StudentRepository implements IStudentRepository {

    private List<Student> studentsList = new ArrayList<>();

    @Override
    public void addStudent(Student student) {
        studentsList.add(student);
    }

    @Override
    public void deleteStudent(String studentId) {
        studentsList.removeIf(student -> student.getPersonID().equals(studentId));
    }

    @Override
    public void updateStudent(Student student) {
        studentsList.remove(student);
        studentsList.add(student);
    }

    @Override
    public Optional<Student> getStudentById(String studentId) {
        return studentsList.stream()
                .filter(student -> student.getPersonID().equals(studentId))
                .findFirst();
    }

    @Override
    public List<Student> getAllStudents() {
        return studentsList;
    }
}
