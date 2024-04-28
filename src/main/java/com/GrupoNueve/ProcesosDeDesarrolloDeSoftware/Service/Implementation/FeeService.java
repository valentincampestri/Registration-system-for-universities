package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Service.Implementation;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.Response.FeeResponseDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.Response.MessageResponseDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity.Course;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity.Fee;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity.Inscription;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity.Student;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Repository.ICourseRepository;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Repository.IFeeRepository;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Repository.IStudentRepository;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Service.IFeeService;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.utills.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FeeService implements IFeeService {
    IFeeRepository feeRepository;

    public FeeService(IFeeRepository feeRepository) {
        this.feeRepository = feeRepository;
    }

    @Override
    public void addFee(Inscription inscription) {
        List<Fee> existentFees = feeRepository.getFeeByStudentCode(inscription.getStudent().getPersonCode());
        Double price = inscription.getCourses().stream().mapToDouble(Course::getPrice).sum();
        for (Fee fee : existentFees) {
            if (!fee.getIsPaid()) {
                List<Inscription> actualInscriptions = fee.getInscriptions();
                actualInscriptions.add(inscription);
                fee.setInscriptions(actualInscriptions);
                fee.setPrice(fee.getPrice() + price);
                feeRepository.updateFee(fee);
                return;
            }
        }
        Fee newFee = new Fee(List.of(inscription), price, false);
        feeRepository.addFee(newFee);
    }

    @Override
    public MessageResponseDto payFee(String feeCode, String paymentMethod) {
        return null;
    }

    @Override
    public List<FeeResponseDto> getFeesByStudent(String studentCode) {
        List<Fee> feeList = feeRepository.getFeeByStudentCode(studentCode);
        return  feeList.stream().map(Mapper::convertFeeToFeeResponseDto).toList();
    }
}
