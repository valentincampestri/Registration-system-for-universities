package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Repository.Implementation;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Entity.Fee;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Repository.IFeeRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class FeeRepository implements IFeeRepository {

    private List<Fee> feesList = new ArrayList<>();

    @Override
    public void addFee(Fee fee) {
        feesList.add(fee);
    }

    @Override
    public void deleteFee(String feeId) {
        feesList.removeIf(fee -> feeId.equals(feeId));
    }

    @Override
    public void updateFee(Fee fee) {
        feesList.remove(fee);
        feesList.add(fee);
    }

    @Override
    public Optional<Fee> getFeeById(String feeId) {
        return feesList.stream()
                .filter(fee -> fee.getFeeId().equals(feeId))
                .findFirst();
    }

    @Override
    public List<Fee> getAllFees() {
        return feesList;
    }
}
