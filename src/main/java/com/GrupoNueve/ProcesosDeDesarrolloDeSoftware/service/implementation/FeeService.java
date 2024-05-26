package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.service.implementation;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.dto.response.FeeResponseDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.dto.response.MessageResponseDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.entity.*;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.exception.BadRequestException;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.exception.NotFoundException;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.repository.IFeeRepository;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.repository.IStudentRepository;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.service.IFeeService;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.utils.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FeeService implements IFeeService {
    IFeeRepository feeRepository;
    IStudentRepository studentRepository;

    public FeeService(IFeeRepository feeRepository, IStudentRepository studentRepository) {
        this.feeRepository = feeRepository;
        this.studentRepository = studentRepository;
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
    public MessageResponseDto pay(String studentCode, String paymentMethod) {
        List<Fee> feeList = feeRepository.getFeeByStudentCode(studentCode);
        for (Fee fee : feeList) {
            if (!fee.getIsPaid()) {
                IPaymentMethod selectedPaymentMethod = PaymentMethodFactory.createPaymentMethod(paymentMethod);

                if (selectedPaymentMethod.pay(fee.getPrice())){
                    fee.setIsPaid(true);
                    feeRepository.updateFee(fee);
                    return new MessageResponseDto("The fee has been paid successfully.");
                }
            }
        }
        throw new BadRequestException("The student does not have any pending fees.");
    }

    @Override
    public List<FeeResponseDto> getFeesByStudent(String studentCode) {
        Optional<Student> existentStudent = studentRepository.getStudentByCode(studentCode);
        if (existentStudent.isEmpty()) {
            throw new NotFoundException("The student does not exist.");
        }
        List<Fee> feeList = feeRepository.getFeeByStudentCode(studentCode);
        if (feeList.isEmpty()) {
            throw new BadRequestException("The student does not have fees.");
        }
        return feeList.stream().map(Mapper::convertFeeToFeeResponseDto).toList();
    }
}
