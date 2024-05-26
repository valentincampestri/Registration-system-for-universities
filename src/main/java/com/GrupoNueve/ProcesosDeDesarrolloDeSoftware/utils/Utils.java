package com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.utils;

import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.entity.Inscription;
import com.GrupoNueve.ProcesosDeDesarrolloDeSoftware.service.IFeeService;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Utils {
    IFeeService feeService;

    public Utils(IFeeService feeService) {
        this.feeService = feeService;
    }

    public void addFee(Inscription inscription) {
        feeService.addFee(inscription);
    }
}