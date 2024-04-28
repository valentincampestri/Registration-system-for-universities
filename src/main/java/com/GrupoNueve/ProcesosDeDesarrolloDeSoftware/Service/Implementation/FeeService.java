package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Service.Implementation;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.Response.FeeResponseDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.Response.MessageResponseDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity.Course;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity.Fee;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity.Student;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Repository.ICourseRepository;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Repository.IFeeRepository;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Repository.IStudentRepository;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Service.IFeeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeeService implements IFeeService {
    IFeeRepository feeRepository;
    IStudentRepository studentRepository;

    public FeeService(IFeeRepository feeRepository) {
        this.feeRepository = feeRepository;
    }

    @Override
    public void addFee(List<Course> courses, Student student) {
        Double price = courses.stream().mapToDouble(Course::getPrice).sum();
        Fee newFee = new Fee(courses, student, price, false);
        feeRepository.addFee(newFee);
        List<Fee> newStudentFees = student.getFeeList();
        newStudentFees.add(newFee);
        student.setFeeList(newStudentFees);
        studentRepository.updateStudent(student);
    }

    @Override
    public MessageResponseDto payFee(String feeCode, String paymentMethod) {
        return null;
    }

    @Override
    public List<FeeResponseDto> getFeesByStudent(String studentId) {
        return null;
    }
}
