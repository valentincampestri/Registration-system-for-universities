package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Service.Implementation;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Repository.IStudentRepository;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Service.IStudentService;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class StudentService implements IStudentService {
    IStudentRepository studentRepository;

    public StudentService(IStudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
}
