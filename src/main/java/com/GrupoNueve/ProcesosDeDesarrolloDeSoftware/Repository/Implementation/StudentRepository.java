package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Repository.Implementation;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity.Student;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity.Student;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Repository.IStudentRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class StudentRepository implements IStudentRepository {

    private List<Student> studentsList = new ArrayList<>();

    public StudentRepository() throws IOException {
        loadStudents();
    }

    @Override
    public void addStudent(Student student) {
        studentsList.add(student);
    }

    @Override
    public void deleteStudent(String studentCode) {
        studentsList.removeIf(student -> student.getPersonCode().equals(studentCode));
    }

    @Override
    public void updateStudent(Student student) {
        studentsList.remove(student);
        studentsList.add(student);
    }

    @Override
    public Optional<Student> getStudentByCode(String studentCode) {
        return studentsList.stream()
                .filter(student -> student.getPersonCode().equals(studentCode))
                .findFirst();
    }

    @Override
    public List<Student> getAllStudents() {
        return studentsList;
    }

    private void loadStudents() throws IOException {
        File file;
        ObjectMapper objectMapper = new ObjectMapper();
        List<Student> mappedStudents;

        try {
            file = ResourceUtils.getFile("classpath:Student.json");

            objectMapper.registerModule(new JavaTimeModule());
            objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            objectMapper.setDateFormat(new SimpleDateFormat("dd-MM-yyyy"));
            mappedStudents = objectMapper.readValue(file, new TypeReference<List<Student>>() {
            });
            studentsList = mappedStudents;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println(LocalDate.now());
        }
    }
}
