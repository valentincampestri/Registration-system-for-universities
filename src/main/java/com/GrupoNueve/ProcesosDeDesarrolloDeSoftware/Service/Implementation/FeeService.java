package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Service.Implementation;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.Response.FeeResponseDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Dto.Response.MessageResponseDto;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity.*;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Repository.IFeeRepository;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Service.IFeeService;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.utils.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public MessageResponseDto pay(String studentCode, String paymentMethod) {
        /*List<Fee> feeList = feeRepository.getFeeByStudentCode(studentCode);
        for (Fee fee : feeList) {
            if (!fee.getIsPaid()) {
                String normalizedPaymentMethod = paymentMethod.toLowerCase();
                IPaymentMethod paymentMethod1 = switch (normalizedPaymentMethod) {
                    case "mercadopago":
                        new MercadoPago();
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid payment method: " + paymentMethod);
                };

                fee.setIsPaid(true);
                feeRepository.updateFee(fee);
                return new MessageResponseDto("The fee has been paid successfully.");
            }
        }*/
        return new MessageResponseDto("The student has no unpaid fees.");
    }

    @Override
    public List<FeeResponseDto> getFeesByStudent(String studentCode) {
        List<Fee> feeList = feeRepository.getFeeByStudentCode(studentCode);
        return feeList.stream().map(Mapper::convertFeeToFeeResponseDto).toList();
    }
}
