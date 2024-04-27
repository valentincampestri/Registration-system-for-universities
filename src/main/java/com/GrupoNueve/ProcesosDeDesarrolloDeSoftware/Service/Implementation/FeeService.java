package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Service.Implementation;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Repository.IFeeRepository;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.Service.IFeeService;
import org.springframework.stereotype.Service;

@Service
public class FeeService implements IFeeService {
    IFeeRepository feeRepository;

    public FeeService(IFeeRepository feeRepository) {
        this.feeRepository = feeRepository;
    }
}
