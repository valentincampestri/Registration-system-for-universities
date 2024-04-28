package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Service;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.Response.FeeResponseDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.Response.MessageResponseDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity.Course;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity.Fee;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity.Student;

import java.util.List;

public interface IFeeService {

    void addFee(List<Course> courses, Student student);
    MessageResponseDto payFee(String feeCode, String paymentMethod);
    List<FeeResponseDto> getFeesByStudent(String studentId);
}
