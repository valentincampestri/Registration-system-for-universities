package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.repository.implementation;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.entity.Fee;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.repository.IFeeRepository;
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

@Repository
public class FeeRepository implements IFeeRepository {

    private List<Fee> feesList = new ArrayList<>();

    public FeeRepository() throws IOException {
        loadFees();
    }

    @Override
    public void addFee(Fee fee) {
        feesList.add(fee);
    }

    @Override
    public void deleteFee(String feeCode) {
        feesList.removeIf(fee -> fee.getFeeCode().equals(feeCode));
    }

    @Override
    public void updateFee(Fee fee) {
        feesList.remove(fee);
        feesList.add(fee);
    }

    @Override
    public List<Fee> getFeeByStudentCode(String StudentCode) {
        return feesList.stream()
                .filter(fee -> fee.getInscriptions().stream()
                        .anyMatch(inscription -> inscription.getStudent().getPersonCode().equals(StudentCode)))
                .toList();
    }

    @Override
    public List<Fee> getAllFees() {
        return feesList;
    }

    private void loadFees() {
        File file;
        ObjectMapper objectMapper = new ObjectMapper();
        List<Fee> mappedFees;

        try {
            file = ResourceUtils.getFile("classpath:Fee.json");

            objectMapper.registerModule(new JavaTimeModule());
            objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            objectMapper.setDateFormat(new SimpleDateFormat("dd-MM-yyyy"));
            mappedFees = objectMapper.readValue(file, new TypeReference<>() {
            });
            feesList = mappedFees;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println(LocalDate.now());
        }
    }
}
